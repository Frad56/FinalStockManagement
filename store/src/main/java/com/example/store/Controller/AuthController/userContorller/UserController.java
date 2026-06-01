package com.example.store.controller.authController.userContorller;


import com.example.store.model.authentification.User;
import com.example.store.service.AuthService.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/ListUsers")
    public ResponseEntity<List<User>> fetchUsersList() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

}
