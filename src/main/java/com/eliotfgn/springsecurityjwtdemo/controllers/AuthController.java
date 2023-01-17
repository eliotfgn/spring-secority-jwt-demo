package com.eliotfgn.springsecurityjwtdemo.controllers;

import com.eliotfgn.springsecurityjwtdemo.domain.SignupRequest;
import com.eliotfgn.springsecurityjwtdemo.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        authService.signup(signupRequest);
         return ResponseEntity.ok().body("Signed up");
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SignupRequest signupRequest) {
        return ResponseEntity.ok().body(authService.login(signupRequest));
    }
}
