package com.platform.service.impl;

import com.platform.common.enums.Role;
import com.platform.dto.req.SignUpRequest;
import com.platform.dto.req.SigninRequest;
import com.platform.dto.res.JwtAuthenticationResponse;
import com.platform.entities.User;
import com.platform.repositories.postgres.UserRepository;
import com.platform.service.AuthenticationService;
import com.platform.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest signUpRequest) {
        var user = User.builder().firstName(signUpRequest.getFirstName()).lastName(signUpRequest.getLastName())
                .email(signUpRequest.getEmail()).password(passwordEncoder.encode(signUpRequest.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().access_token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
        var user = userRepository.findByEmail(signinRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);

        return JwtAuthenticationResponse.builder().access_token(jwt).build();
    }




}