package com.tracker.task.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import com.tracker.task.entity.Project;
import com.tracker.task.model.ProjectRequestModel;
import com.tracker.task.service.ProjectService;

import com.tracker.task.util.SecurityUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private SecurityUtil securityUtil;

    @PostMapping("/project/add")
    public ResponseEntity<?> createProject(@Valid @RequestBody ProjectRequestModel project) throws AccessDeniedException {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.projectService.createProject(project, securityUtil.getUserId()));
    }

    @GetMapping("/project")
    public ResponseEntity<List<Project>> getUserProjects() throws AccessDeniedException {
        return projectService.getProjectsByUser(securityUtil.getUserId());
    }


}
