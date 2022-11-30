package com.eliotfgn.springsecurityjwtdemo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping
    public ResponseEntity<?> signup() {
         return ResponseEntity.ok().body("Signing up");
    }
}
