package com.tracker.task.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.tracker.task.model.UserRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tracker.task.entity.User;
import com.tracker.task.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userrepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User createUser(UserRequestModel user) {
        Optional<User> existingUser = userrepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists with this email");
        }
        return userrepository.save(mapToUserEntity(user));
    }

    private User mapToUserEntity(UserRequestModel user) {
        User userEntity = new User();
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userrepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
