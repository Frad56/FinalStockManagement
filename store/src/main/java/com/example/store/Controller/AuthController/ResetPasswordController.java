package com.example.store.controller.AuthController;




import com.example.store.dto.authentification.EmailRequestDTO;
import com.example.store.dto.authentification.OldPasswordVerification;
import com.example.store.dto.authentification.ResetPasswordRequest;
import com.example.store.service.AuthService.settings.resetPasswordService.interfaces.ResetPasswordService;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class ResetPasswordController {


  public final ResetPasswordService resetPasswordService;

  public ResetPasswordController(ResetPasswordService resetPasswordService){
      this.resetPasswordService =resetPasswordService;
  }

  //POST /auth/verify-password

//   @PostMapping("/verify-password")
//   public ResponseEntity<String> verifyPassword(){
//
//   }

    @PutMapping("/reset-password")
    public ResponseEntity<Map<String,String>>  adminResetPassword(@RequestBody ResetPasswordRequest request){
        resetPasswordService.changePassword(request);
        return ResponseEntity.ok(Map.of("message","Password changed successfully."));
    }
    
  @PostMapping("/verify-password")
    public ResponseEntity<Map<String,String>> existsByEmail(@RequestBody EmailRequestDTO email){
      resetPasswordService.existsByEmail(email);
        return ResponseEntity.ok(Map.of("message", "this email doesn't exist"));
  }

  @PostMapping("/verify-email")
  public ResponseEntity<Map<String,String>> verifyOldPassword(@RequestBody OldPasswordVerification oldPasswordVerification){

      resetPasswordService.oldPasswordVerification(oldPasswordVerification);
      return ResponseEntity.ok(Map.of("message", "old password is correct"));
  }


    @PostMapping("/verify-email-exists")
    public ResponseEntity<Map<String,String>> verifyEmailExists(@RequestBody EmailRequestDTO emailRequestDTO){
        boolean existsByEmail= resetPasswordService.existsByEmailAndNotAdmin(emailRequestDTO);
        return ResponseEntity.ok(Map.of("message",  existsByEmail ? "Email exists" : "Email does not exist"));
    }




}
