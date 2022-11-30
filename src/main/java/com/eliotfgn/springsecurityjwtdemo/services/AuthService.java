package com.eliotfgn.springsecurityjwtdemo.services;

import com.eliotfgn.springsecurityjwtdemo.domain.SignupRequest;
import com.eliotfgn.springsecurityjwtdemo.domain.User;
import com.eliotfgn.springsecurityjwtdemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    public void signup(SignupRequest payload) {
        User user = new User(payload.getUsername(), payload.getPassword());

        userRepository.save(user);
    }

    private String login(SignupRequest payload) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(payload.getUsername(), payload.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        return jwt;
    }
}
