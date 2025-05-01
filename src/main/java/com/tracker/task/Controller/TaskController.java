package com.tracker.task.controller;

import com.tracker.task.model.TaskRequestModel;
import com.tracker.task.service.TaskService;

import com.tracker.task.util.SecurityUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;


@RestController
public class TaskController {

    @Autowired
    private TaskService taskservice;

    @Autowired
    private SecurityUtil securityUtil;

    @PostMapping("/task/add/{projectId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> createTask(@PathVariable Long projectId, @Valid @RequestBody TaskRequestModel task) throws AccessDeniedException {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.taskservice.createTask(projectId, task,securityUtil.getUserId()));
    }


    @GetMapping("/task/{taskId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> getById(@PathVariable Long taskId) throws AccessDeniedException {
        return ResponseEntity.ok(this.taskservice.getById(taskId, securityUtil.getUserId()));
    }


    @PutMapping(path = "/task/{taskId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> updateTask(@PathVariable Long taskId, @Valid @RequestBody TaskRequestModel updatedTask) throws AccessDeniedException {
        return ResponseEntity.ok(this.taskservice.updateTask(taskId, updatedTask, securityUtil.getUserId()));
    }


    @DeleteMapping("/task/{taskId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) throws AccessDeniedException {
        this.taskservice.deleteTask(taskId, securityUtil.getUserId());
        return ResponseEntity.ok().build();
    }
}

