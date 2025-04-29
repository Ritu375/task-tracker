package com.tracker.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tracker.task.entity.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query(value = "select * from project where user_id = :userId", nativeQuery = true)
    List<Project> findByUser(@Param("userId") Long userId);


}
