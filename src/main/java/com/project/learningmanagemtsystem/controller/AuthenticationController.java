package com.project.learningmanagemtsystem.controller;

import com.project.learningmanagemtsystem.model.AuthenticationResponse;
import com.project.learningmanagemtsystem.model.User;
import com.project.learningmanagemtsystem.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody User request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody User request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/refresh_token")
    public ResponseEntity refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        return authService.refreshToken(request, response);
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = authService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable int id) {
        return authService.deleteUserById(id);
    }
    @PutMapping("/course-adding/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable("id") int id,
            @RequestBody User userUpdates) {
        return authService.updateUser(id, userUpdates);
    }


    @GetMapping("/userbyid/{userId}")
    public ResponseEntity<AuthenticationResponse> getUserById(@PathVariable("userId") int userId) {
        return authService.getUserById(userId);
    }
}
