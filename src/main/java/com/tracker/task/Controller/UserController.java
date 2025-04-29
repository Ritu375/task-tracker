package com.tracker.task.controller;

import com.tracker.task.model.AuthRequest;
import com.tracker.task.model.AuthResponse;
import com.tracker.task.model.UserRequestModel;
import com.tracker.task.util.JwtTokenUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.tracker.task.entity.User;
import com.tracker.task.service.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticatetionManager;

    @Autowired
    private JwtTokenUtil jwtUtil;


    @PostMapping("/user/signup")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestModel user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.createUser(user));
    }

    @GetMapping("/user/all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        authenticatetionManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()
                ));

        final UserDetails userDetails = userService.loadUserByUsername(request.getUserName());
        final String token = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
