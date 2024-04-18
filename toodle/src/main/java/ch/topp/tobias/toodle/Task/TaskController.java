package ch.topp.tobias.toodle.Task;


import ch.topp.tobias.toodle.security.Roles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class TaskController {

    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("api/tasks")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<List<Task>> getSortedTasks() {
        List<Task> tasks = taskService.getTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("api/task/{id}")
    @RolesAllowed(Roles.Read)
    public ResponseEntity<Task> one(@PathVariable Long id) {
        Task task = taskService.getTask(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping("api/task/create")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.insertTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("api/task/update/{id}")
    @RolesAllowed(Roles.Update)
    public ResponseEntity<Task> updateTask(@RequestBody Task task, @PathVariable Long id) {
        Task updatedTask = taskService.updateTask(task, id);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("api/task/delete/{id}")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
