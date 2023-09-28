package com.platform.service;


import com.platform.dto.req.RefreshTokenRequest;
import com.platform.dto.req.SignUpRequest;
import com.platform.dto.req.SigninRequest;
import com.platform.dto.res.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signin(SigninRequest signinRequest);


}