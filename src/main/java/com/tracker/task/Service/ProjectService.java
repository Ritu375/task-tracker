package com.tracker.task.service;


import com.tracker.task.entity.Project;
import com.tracker.task.model.ProjectRequestModel;
import com.tracker.task.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectrepository;

    @Autowired
    private UserService userService;

    @Transactional
    public ProjectRequestModel createProject(ProjectRequestModel project,Long userId) {
        List<Project> userProjects = projectrepository.findByUser(userId);
        if (userProjects.size() >= 4) {
            throw new RuntimeException("Maximum 4 projects allowed per user");
        }
         projectrepository.save(mapToProjectEntity(project, userId));
        return project;
    }

    private Project mapToProjectEntity(ProjectRequestModel project, Long userId) {
        Project projectEntity = new Project();
        projectEntity.setTitle(project.getTitle());
        projectEntity.setUser(userService.findById(userId));
        return projectEntity;
    }

    @Transactional
    public ResponseEntity<List<Project>> getProjectsByUser(Long userId) {
        return ResponseEntity.ok(projectrepository.findByUser(userId));
    }

    @Transactional
    public Project findProjectById(Long projectId) {
        return projectrepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));
    }
}
