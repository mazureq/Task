package com.crud.tasks.trello.client;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Title", "Content");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        Assert.assertEquals("Title", task.getTitle());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "Title", "Content");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        Assert.assertEquals("Title", taskDto.getTitle());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        List<Task> taskList = new ArrayList<>();
        Task task = new Task(1L, "Title", "Content");
        Task task1 = new Task(2L, "Title1", "Content1");
        Task task2 = new Task(2L, "Title2", "Content2");
        taskList.add(task);
        taskList.add(task1);
        taskList.add(task2);
        //When
        List<TaskDto>taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        //Then
        Assert.assertEquals("Title1", taskDtoList.get(1).getTitle());

    }
}
