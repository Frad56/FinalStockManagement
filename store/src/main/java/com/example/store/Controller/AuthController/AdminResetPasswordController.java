package com.example.store.Controller.AuthController;




import com.example.store.DTO.authentification.OldPasswordVerification;
import com.example.store.DTO.authentification.ResetPasswordRequest;
import com.example.store.Service.AuthService.AdminSettings.adminResetPasswordService.interfaces.AdminResetPasswordService;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/auth/admin")
public class AdminResetPasswordController {


  public final AdminResetPasswordService adminResetPasswordService;

  public AdminResetPasswordController(AdminResetPasswordService adminResetPasswordService){
      this.adminResetPasswordService =adminResetPasswordService;
  }

  //POST /auth/verify-password

//   @PostMapping("/verify-password")
//   public ResponseEntity<String> verifyPassword(){
//
//   }


  @PutMapping("/reset-password")
  public ResponseEntity<Map<String,String>>  adminResetPassword(@RequestBody ResetPasswordRequest request){
      adminResetPasswordService.changePassword(request);
      return ResponseEntity.ok(Map.of("message","Password changed successfully."));
  }


  @PostMapping("/verify-password")
  public ResponseEntity<Map<String,String>> adminPassword(@RequestBody OldPasswordVerification oldPasswordVerification){

      adminResetPasswordService.oldPasswordVerification(oldPasswordVerification);
      return ResponseEntity.ok(Map.of("message", "old password is correct"));
  }




}
