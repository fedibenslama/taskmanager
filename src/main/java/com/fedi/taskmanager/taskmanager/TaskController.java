package com.fedi.taskmanager.taskmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")

public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    //Get All Tasks
    @GetMapping
    public List<Task> getAllTasks(){
        return taskRepository.findAll();

    }
    //Create a new Task
    @PostMapping
    public Task createTask(@RequestBody Task task){
        return taskRepository.save(task);
    }
    //Get a Task by Id
    @GetMapping("/{id}")            //This maps a GET request for a specific task by ID.
    public ResponseEntity<Task> getTaskById(@PathVariable String id){
        Task task = taskRepository.findById(id).orElse(null);
        if(task != null){
            return ResponseEntity.ok(task);
        } else{
            return ResponseEntity.notFound().build();
        }
    }
    //Update Task
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody Task updatedTask){
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(updatedTask.getTitle());
                    task.setDescription(updatedTask.getDescription());
                    task.setCompleted(updatedTask.isCompleted());
                    task.setStatus(updatedTask.getStatus());
                    Task savedTask = taskRepository.save(task);
                    return ResponseEntity.ok(savedTask);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Delete a Task
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable String id) {
        return taskRepository.findById(id)
                .map(task -> {
                    taskRepository.delete(task);
                    return ResponseEntity.noContent().build(); // This part is fine
                })
                .orElseGet(() -> ResponseEntity.notFound().build()); // This part is also fine
    }
    //Filter By Status
    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable String status){    //Using Task: If we returned just a single Task, it would only be suitable for scenarios where you expect a single result (like getting a task by its ID). However, since a status can correspond to multiple tasks, a List<Task> is more appropriate.
        return taskRepository.findByStatus(status); //@PathVariable binds the {status} part of the URL to the status parameter in the method.
    }
    ////Get All Tasks by Pagination and sorting
    @GetMapping("/paginated")
    public Page<Task> getAllTasks(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "title") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort)); // Create a Pageable instance with sorting
        return taskRepository.findAll(pageable);
    }
    //Adding Search by Title or Description
    @GetMapping("/search")
    public List<Task> searchTasks(@RequestParam String keyword){
        return taskRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword); //we’re passing the keyword argument twice, once for each field being searched.
    }

}
