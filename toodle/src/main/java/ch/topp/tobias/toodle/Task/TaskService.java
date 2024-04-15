package ch.topp.tobias.toodle.Task;

import java.util.List;

public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }


    public List<Task> getTasks() {
        return repository.findByOrderByNameAsc();
    }
}
