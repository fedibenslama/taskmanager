package com.fedi.taskmanager.taskmanager;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // Custom query to find tasks by their status
    List<Task> findByStatus(String status);

}
