package ch.topp.tobias.toodle;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ch.topp.tobias.toodle.Task.Task;
import ch.topp.tobias.toodle.Task.TaskRepository;
import ch.topp.tobias.toodle.Task.TaskService;
import ch.topp.tobias.toodle.base.MessageResponse;
import ch.topp.tobias.toodle.storage.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TaskServiceTests {

    @Mock
    private TaskRepository repository;

    @InjectMocks
    private TaskService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetTasks() {
        Task task1 = new Task(); // Stellen Sie sicher, dass die Felder nach Bedarf initialisiert werden
        Task task2 = new Task();
        when(repository.findByOrderByTaskName()).thenReturn(Arrays.asList(task1, task2));

        List<Task> tasks = service.getTasks();
        assertNotNull(tasks);
        assertEquals(2, tasks.size());
    }

    @Test
    public void testGetTask() {
        Task task = new Task();
        when(repository.findById(1L)).thenReturn(Optional.of(task));

        Task result = service.getTask(1L);
        assertNotNull(result);
    }

    @Test
    public void testGetTaskNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.getTask(1L));
    }

    @Test
    public void testInsertTask() {
        Task task = new Task();
        when(repository.save(task)).thenReturn(task);

        Task savedTask = service.insertTask(task);
        assertNotNull(savedTask);
    }

    @Test
    public void testDeleteTask() {
        doNothing().when(repository).deleteById(1L);
        MessageResponse response = service.deleteTask(1L);

        assertNotNull(response);
        assertEquals("Task 1 deleted", response.getMessage());
    }
}

