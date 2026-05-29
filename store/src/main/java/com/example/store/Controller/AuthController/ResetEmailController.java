package com.example.store.controller.AuthController;


import com.example.store.dto.authentification.resetEmail.ResetEmailRequest;
import com.example.store.service.AuthService.UserService;
import com.example.store.service.AuthService.settings.resetEmailService.interfaces.ResetEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("api/auth")
public class ResetEmailController {


    private final ResetEmailService adminResetEmailService;
    private final UserService userService;



    @Autowired
    public ResetEmailController(ResetEmailService adminResetEmailService,
                                UserService userService) {
        this.adminResetEmailService = adminResetEmailService;
        this.userService = userService;

    }


    @GetMapping("/me/email")
    public ResponseEntity<Map<String,String>> getMyEmail(Authentication authentication) {
        String username = authentication.getName();
        String email = userService.findEmailByName(username);
        return ResponseEntity.ok(Map.of("email", email));
    }

    @PutMapping("/admin/reset")
    public ResponseEntity<Map<String,String>> resetEmail(@RequestBody ResetEmailRequest request) {
        adminResetEmailService.resetEmail(request);
        return ResponseEntity.ok(Map.of("message", "Email reset successfully"));
    }





}
