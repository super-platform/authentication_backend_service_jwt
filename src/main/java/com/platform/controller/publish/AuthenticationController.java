package com.platform.controller.publish;

import com.platform.dto.req.SignUpRequest;
import com.platform.dto.req.SigninRequest;
import com.platform.dto.res.JwtAuthenticationResponse;
import com.platform.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public JwtAuthenticationResponse signup(@RequestBody SignUpRequest signUpRequest) {
        return authenticationService.signup(signUpRequest);
    }

    @PostMapping("/signin")
    public JwtAuthenticationResponse signin(@RequestBody SigninRequest signinRequest) {
        return authenticationService.signin(signinRequest);
    }
}