package com.fedi.taskmanager.taskmanager;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {
    // Custom query to find tasks by their status
    List<Task> findByStatus(String status);

}
