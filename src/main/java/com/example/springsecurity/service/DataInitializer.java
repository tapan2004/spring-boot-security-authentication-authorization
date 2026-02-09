package com.example.springsecurity.service;

import com.example.springsecurity.entity.Role;
import com.example.springsecurity.entity.RoleName;
import com.example.springsecurity.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.count() == 0) {
                roleRepository.save(new Role(null, RoleName.USER));
                roleRepository.save(new Role(null, RoleName.ADMIN));
                System.out.println("Default roles inserted");
            }
        };
    }
}