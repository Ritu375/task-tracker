package com.tracker.task.controller;

import java.util.List;

import com.tracker.task.entity.Project;
import com.tracker.task.model.ProjectRequestModel;
import com.tracker.task.service.ProjectService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("/project/add")
    public ResponseEntity<?> createProject(@Valid @RequestBody ProjectRequestModel project) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.projectService.createProject(project));
    }

    @GetMapping("/project")
    public ResponseEntity<List<Project>> getUserProjects() {
        return projectService.getProjectsByUser(1111L);
    }


}
