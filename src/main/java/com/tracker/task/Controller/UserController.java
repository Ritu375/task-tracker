package com.tracker.task.controller;

import com.tracker.task.model.UserRequestModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.tracker.task.entity.User;
import com.tracker.task.service.UserService;

@RestController
public class UserController {
     @Autowired
     private  UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestModel user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.createUser(user));
    }

    @GetMapping("/user/all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }
}
