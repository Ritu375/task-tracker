package com.tracker.task.repository;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tracker.task.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{
    
    Optional<User> findByName(String name);


    Optional<User> findByEmail(@NotBlank String email);
}
