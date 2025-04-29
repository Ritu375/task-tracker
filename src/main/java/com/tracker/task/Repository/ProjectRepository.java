package com.tracker.task.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tracker.task.Entity.Project;

public interface ProjectRepository extends JpaRepository<Project,Long>{

    
 List<Project> findByProjectId(Long projectId);
    
}
