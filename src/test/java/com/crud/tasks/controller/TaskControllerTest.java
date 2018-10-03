package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void testGetTasks() throws Exception {
        //Given
        List<Task> tasksList = new ArrayList<>();
        tasksList.add(new Task(1L, "Test Title", "Test Content"));
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(1L, "Test Title","Test Content"));

        when(dbService.getAllTasks()).thenReturn(tasksList);
        when(taskMapper.mapToTaskDtoList(tasksList)).thenReturn(taskDtoList);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testGetTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Title", "Content");
        Task task = new Task(1L, "Title", "Content");

        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(dbService.getTask(1L)).thenReturn(Optional.of(task));

        //When & Then
        mockMvc.perform(get("/v1/task/getTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Title")));
    }

    @Test
    public void testDeleteTask() throws Exception {

        mockMvc.perform(delete("/v1/task/deleteTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateTask() throws Exception {
        //Given
        TaskDto theTaskDto = new TaskDto(1L, "Test Title", "Test Content");
        Task task = new Task(1L, "Test Title", "Test Content");

//        when(taskMapper.mapToTask(theTaskDto)).thenReturn(task);
//        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(theTaskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(theTaskDto);

        //When & Then
        mockMvc.perform(put("/v1/task/updateTask").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Test Title")));
    }

    @Test
    public void testCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test Title", "Test Content");


        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc.perform(post("/v1/task/createTask").contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(jsonContent))
                .andExpect(status().isOk());
    }

}