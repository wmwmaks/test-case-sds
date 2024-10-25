package org.example.sdstest.controller;

import lombok.RequiredArgsConstructor;
import org.example.sdstest.dto.LoginRequest;
import org.example.sdstest.service.token.TokenService;
import org.example.sdstest.service.user.CustomUserDetailsService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final TokenService tokenService;
    private final CustomUserDetailsService userDetailsService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        if (!userDetails.getPassword().equals(request.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        String accessToken = tokenService.generateAccessToken(userDetails.getUsername());
        return "Access Token: " + accessToken;
    }

    @GetMapping("/protected")
    public String protectedEndpoint(@RequestHeader("Authorization") String token) {
        if (!tokenService.validateAccessToken(token)) {
            throw new RuntimeException("Invalid access token");
        }
        String username = tokenService.getUsernameFromToken(token);
        return "Welcome, " + username;
    }
}
