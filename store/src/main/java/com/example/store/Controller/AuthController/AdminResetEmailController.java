package com.example.store.Controller.AuthController;


import com.example.store.DTO.authentification.resetEmail.ResetEmailRequest;
import com.example.store.Service.AuthService.UserService;
import com.example.store.Service.AuthService.AdminSettings.adminResetEmailSerivce.interfaces.AdminResetEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("api/v1/auth")
public class AdminResetEmailController {


    private final AdminResetEmailService adminResetEmailService;
    private final UserService userService;



    @Autowired
    public AdminResetEmailController(AdminResetEmailService adminResetEmailService,
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
