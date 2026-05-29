package com.example.store.controller.AuthController;


import com.example.store.dto.authentification.EmailRequestDTO;
import com.example.store.model.authentification.VerificationCode;
import com.example.store.service.AuthService.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("api/email")
public class SendCodeController {

    @Autowired
    private final EmailService emailService;

    public SendCodeController(EmailService emailService){
        this.emailService =emailService;
    }


    @PostMapping("/send-email")
    public ResponseEntity<Map<String,String> > sendEmail(@RequestBody EmailRequestDTO request) {

        System.out.println("Email:"+request.getEmail());
        String code = emailService.generateCode();
        emailService.saveCode(request.getEmail(), code);
        emailService.sendEmail(
                request.getEmail(),
                "Reset Email",
                "Your code is: " + code
        );

        return ResponseEntity.ok(Map.of("message", "Email sent successfully"));
    }


    @PostMapping("/verify-code")
    public ResponseEntity<Map<String, String>> verifyCode(@RequestBody VerificationCode request) {

        String storedCode = emailService.getCode(request.getEmail());

        if (storedCode == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Code expired or not found"));
        }

        if (!storedCode.equals(request.getCode())) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Invalid verification code"));
        }
        return ResponseEntity.ok(Map.of("message", "Code verified successfully"));
    }

}
