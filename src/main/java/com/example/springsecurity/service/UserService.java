package com.example.springsecurity.service;


import com.example.springsecurity.entity.Role;
import com.example.springsecurity.entity.RoleName;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.repository.RoleRepository;
import com.example.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    public Optional<User> findByName(String name) {
        return userRepository.findByUsername(name);
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String requestedRole = user.getUserRole();

        if (requestedRole == null || requestedRole.isBlank()) {
            requestedRole = "USER";
        }
        String finalRole = requestedRole.trim().toUpperCase();

        Role role = roleRepository.findAll()
                .stream()
                .filter(r -> r.getUserRole().name().equals(finalRole))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Role " + finalRole + " not found in DB"));

        user.setRoles(Set.of(role));
        return userRepository.save(user);
    }

    public void updateUser(String username, User user) {
        User existing = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(username);
        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (user.getEmail() != null) {
            existing.setEmail(user.getEmail());
        }
        userRepository.save(existing);
    }
}

//    public void deleteUser(Long id) {
//        userRepository.deleteById(id);
//    }
//
//    public Optional<User> getUserName(String name) {
//        return userRepository.findByUsername(name);
//    }
//
//    public Optional<User> getUserById(Long id) {
//        return userRepository.findById(id);
//    }
//}
