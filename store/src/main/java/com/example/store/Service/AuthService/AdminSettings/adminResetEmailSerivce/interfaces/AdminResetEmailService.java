package com.example.store.Service.AuthService.AdminSettings.adminResetEmailSerivce.interfaces;

import com.example.store.DTO.authentification.UserResponse;
import com.example.store.DTO.authentification.resetEmail.ResetEmailRequest;
import com.example.store.Model.Authentification.Role;
import com.example.store.Model.Authentification.User;

public interface AdminResetEmailService {

    boolean isAdmin(Role role);
    User findUserByEmail(String email);
    UserResponse resetEmail(ResetEmailRequest request);
   // UserResponse resetEmailAndResetCode(ResetEmailRequest request);
    String findEmailByName(String name);
}
