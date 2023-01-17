package com.eliotfgn.springsecurityjwtdemo.services;

import com.eliotfgn.springsecurityjwtdemo.domain.ERole;
import com.eliotfgn.springsecurityjwtdemo.domain.Role;
import com.eliotfgn.springsecurityjwtdemo.domain.SignupRequest;
import com.eliotfgn.springsecurityjwtdemo.domain.User;
import com.eliotfgn.springsecurityjwtdemo.repositories.RoleRepository;
import com.eliotfgn.springsecurityjwtdemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Service
public class AuthService {
    

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JwtUtils jwtUtils;

    public void signup(SignupRequest payload) {
        User user = new User(payload.getUsername(), passwordEncoder.encode(payload.getPassword()));
        Role roleAdmin = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElse(roleRepository.save(new Role(ERole.ROLE_ADMIN)));
        Role roleUser = roleRepository.findByName(ERole.ROLE_USER)
                .orElse(roleRepository.save(new Role(ERole.ROLE_USER)));

        if (user.getUsername() == "admin") {
            user.getRoles().add(roleAdmin);
        } else {
            user.getRoles().add(roleUser);
        }

        userRepository.save(user);
    }

    public String login(SignupRequest payload) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(payload.getUsername(), payload.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        System.out.println("auth");

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        return jwt;
    }
}
