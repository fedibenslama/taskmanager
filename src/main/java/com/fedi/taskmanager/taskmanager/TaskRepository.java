package com.fedi.taskmanager.taskmanager;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
    // Custom query to find tasks by their status
    List<Task> findByStatus(String status);
    Page<Task> findAll(Pageable pageable);

}
