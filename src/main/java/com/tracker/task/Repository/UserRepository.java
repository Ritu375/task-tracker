package com.tracker.task.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tracker.task.Entity.User;

public interface UserRepository extends JpaRepository<User,Long>{
    
    User findByEmail(String email);

    

    
}
