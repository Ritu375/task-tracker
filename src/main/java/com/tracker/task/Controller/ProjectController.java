package com.tracker.task.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tracker.task.Entity.Project;
import com.tracker.task.Service.ProjectService;

@RestController
public class ProjectController {
    @Autowired
    private  ProjectService projectService;

    @PostMapping("/projects")
    public ResponseEntity<?> createProject(@RequestBody Project project) {
        return ResponseEntity.status(HttpStatus.CREATED).body (this.projectService.createProject(project));
    }

    @GetMapping("/projects/{project_id}")
    public List<Project> getUserProjects(@PathVariable Long Id) {
        return projectService.getProjectsByUser(Id);
    }
}
