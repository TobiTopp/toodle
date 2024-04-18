package ch.topp.tobias.toodle.Task;

import ch.topp.tobias.toodle.base.MessageResponse;
import ch.topp.tobias.toodle.storage.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getTasks() {
        return repository.findByOrderByTaskName();
    }

    public Task getTask(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Task.class));
    }

    public Task insertTask(Task task) {
        return repository.save(task);
    }

    public Task updateTask(Task task, Long id) {
        return repository.findById(id)
                .map(taskOrig -> {
                    taskOrig.setTaskId(task.getTaskId());
                    taskOrig.setTaskName(task.getTaskName());
                    taskOrig.setTaskDescription(task.getTaskDescription());
                    taskOrig.setDueDate(task.getDueDate());
                    taskOrig.setProcessorData(task.getProcessorData());
                    taskOrig.setTopicData(task.getTopicData());
                    return repository.save(taskOrig);
                })
                .orElseGet(() -> repository.save(task));
    }


    public MessageResponse deleteTask(Long id) {
        repository.deleteById(id);
        return new MessageResponse("Task " + id + " deleted");
    }
}
