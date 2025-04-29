package com.tracker.task.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.tracker.task.Entity.User;
import com.tracker.task.Service.UserService;

@RestController
public class UserController {
     @Autowired
     private  UserService userService;

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
    
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.createUser(user));
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }
}
