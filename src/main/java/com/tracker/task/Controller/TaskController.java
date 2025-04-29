package com.tracker.task.controller;

import com.tracker.task.model.TaskRequestModel;
import com.tracker.task.service.TaskService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TaskController {

    @Autowired
    private TaskService taskservice;

    @PostMapping("/task/add/{projectId}")
    public ResponseEntity<?> createTask(@PathVariable Long projectId, @Valid @RequestBody TaskRequestModel task) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.taskservice.createTask(projectId, task));
    }


    @GetMapping("/task/{taskId}")
    public ResponseEntity<?> getById(@PathVariable Long taskId) {
        return ResponseEntity.ok(this.taskservice.getById(taskId));
    }


    @PutMapping(path = "/task/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable Long taskId, @Valid @RequestBody TaskRequestModel updatedTask) {
        return ResponseEntity.ok(this.taskservice.updateTask(taskId, updatedTask));
    }


    @DeleteMapping("/task/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        this.taskservice.deleteTask(taskId);
        return ResponseEntity.ok().build();
    }
}

