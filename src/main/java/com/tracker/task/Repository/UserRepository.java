package com.tracker.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tracker.task.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{
    
    User findByEmail(String email);

    

    
}
