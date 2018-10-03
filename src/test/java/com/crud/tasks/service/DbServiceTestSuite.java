package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import io.swagger.models.auth.In;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {

    @InjectMocks
    private DbService dbService;

    @Mock
    TaskRepository taskRepository;

    @Test
    public void testGetAllTasks() {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "Test", "Test content"));
        when(taskRepository.findAll()).thenReturn(taskList);
        //When
        //Then
        assertEquals(1, dbService.getAllTasks().size());
    }
    @Test
    public void testGetTask() {
        //Given
        Task task = new Task(1L, "Test", "Test content");
        when(taskRepository.findById(1L)).thenReturn(Optional.ofNullable(task));
        //When
        Task task1 = dbService.getTask(1L).get();
        //Then
        assertEquals("Test", task1.getTitle());
    }
    @Test
    public void testSaveTask() {
        //Given
        Task task = new Task(1L, "Test", "Test content");
        when(taskRepository.save(task)).thenReturn(task);
        //When
        Task task1 = dbService.saveTask(task);
        //Then
        assertEquals(1L, task1.getId().longValue());
    }
    @Test
    public void deleteTask() {
        //Given
        //When
        dbService.deleteTask(1L);
        //Then
        verify(taskRepository).delete(1L);
    }


}