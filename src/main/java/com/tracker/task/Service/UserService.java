package com.tracker.task.service;

import java.util.List;
import java.util.Objects;

import com.tracker.task.model.UserRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracker.task.entity.User;
import com.tracker.task.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userrepository;

    @Transactional
    public User createUser(UserRequestModel user) {
        User existingUser = userrepository.findByEmail(user.getEmail());
        if (Objects.nonNull(existingUser)) {
            throw new RuntimeException("User already exists with this email");
        }
        return userrepository.save(mapToUserEntity(user));
    }

    private User mapToUserEntity(UserRequestModel user) {
        User userEntity = new User();
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setCountry(user.getCountry());
        return userEntity;
    }

    @Transactional
    public List<User> getAllUsers() {
        return userrepository.findAll();
    }

    @Transactional
    public User findById(Long userId) {
        return userrepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
