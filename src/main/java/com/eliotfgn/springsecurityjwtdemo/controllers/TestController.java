package com.eliotfgn.springsecurityjwtdemo.controllers;

import com.eliotfgn.springsecurityjwtdemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok().body("Hello");
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }
}
