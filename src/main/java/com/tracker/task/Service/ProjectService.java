package com.tracker.task.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.task.Entity.Project;
import com.tracker.task.Repository.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectrepository;

       public Project createProject(Project project) {
        List<Project> userProjects = projectrepository.findByProjectId(project.getUser().getId());
        if (userProjects.size() >= 4) throw new RuntimeException("Maximum 4 projects allowed per user");
        return projectrepository.save(project);
    }

    public List<Project> getProjectsByUser(Long Id) {
        return projectrepository.findByProjectId(Id);
    }
    
}
