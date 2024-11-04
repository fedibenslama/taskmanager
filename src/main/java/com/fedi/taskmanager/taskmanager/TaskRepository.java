package com.fedi.taskmanager.taskmanager;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;


public interface TaskRepository extends MongoRepository<Task, String> {
    // Custom query to find tasks by their status
    List<Task> findByStatus(String status);
    //Page<Task> findAll(Pageable pageable); Remove Unnecessary Code: Since MongoRepository already provides the findAll(Pageable pageable) method, you don’t need to redefine it in your repository interface. Just keep the findByStatus method:
    List<Task> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String titleKeyword,
                                                                                String descriptionKeyword);
}
