package com.example.store.controller.AuthController;

import com.example.store.dto.Response.ApiResponse;
import com.example.store.dto.authentification.LoginRequest;
import com.example.store.dto.authentification.LoginResponse;
import com.example.store.dto.authentification.UserDTO;
import com.example.store.model.authentification.Role;
import com.example.store.model.authentification.User;
import com.example.store.security.jwt.CustomUserDetails;
import com.example.store.security.jwt.JwtUtil;
import com.example.store.security.details.CustomUserDetailsService;
import com.example.store.service.AuthService.AuthService;
import com.example.store.service.AuthService.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {



    private final AuthService authService;
    public AuthController(AuthService authService){
        this.authService = authService;

    }



    @PostMapping("/signin")
    public LoginResponse authenticateUser(@RequestBody LoginRequest user) {
        return authService.authenticateUser(user);
    }


    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> register(@RequestBody UserDTO user){
        authService.register(user);
        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully"));
    }
}
