package com.example.springsecurity;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringSecurityApplication {
    @Autowired
    private PasswordEncoder encoder;

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

//    @PostConstruct
//    public void gen() {
//        System.out.println("NEW HASH = " + encoder.encode("12345"));
//    }
}
