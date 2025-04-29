package com.tracker.task.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tracker.task.Entity.Task;

public interface TaskRepository extends JpaRepository<Task,Long> {

    List<Task> findByTaskId(Long taskId);
    
}
