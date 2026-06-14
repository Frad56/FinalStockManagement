package com.example.store.controller.authController;

import com.example.store.dto.response.ApiResponse;
import com.example.store.dto.authentification.LoginRequest;
import com.example.store.dto.authentification.LoginResponse;
import com.example.store.dto.authentification.UserDTO;
import com.example.store.service.AuthService.AuthService;
import org.springframework.http.ResponseEntity;


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
