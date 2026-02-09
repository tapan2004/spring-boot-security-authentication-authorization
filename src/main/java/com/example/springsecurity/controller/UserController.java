package com.example.springsecurity.controller;


import com.example.springsecurity.entity.User;
import com.example.springsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserDetailsService userDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (user == null) {
            return new ResponseEntity<>("Invalid User Data", HttpStatus.BAD_REQUEST);
        }
        Optional<User> existingUser = userService.findByName(user.getUsername());
        if (existingUser.isPresent()) {
            return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
        }
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()
                    )
            );
            userDetailsService.loadUserByUsername(user.getUsername());
            return new ResponseEntity<>("Login Successful", HttpStatus.OK);

        } catch (Exception e) {
            log.error("Exception occurred while createAuthenticationToken {}", String.valueOf(e));
            return new ResponseEntity<>("Invalid Username or Password", HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/update/{username}")
    @PreAuthorize("#username == authentication.name or hasRole('ADMIN')")
    public ResponseEntity<?> edit(@RequestBody User user,
                                  @PathVariable String username) {
        Optional<User> optionalUser = userService.findByName(username);
        if (optionalUser.isPresent()) {
            userService.updateUser(username, user);
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("User doesn't exit", HttpStatus.NOT_FOUND);
    }
}