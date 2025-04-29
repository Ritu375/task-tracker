package com.tracker.task.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.task.Entity.User;
import com.tracker.task.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userrepository;
     
    public User createUser(User user) {
        return userrepository.save(user);
    }

    public List<User> getAllUsers() {
        return userrepository.findAll();
    }


    
}
