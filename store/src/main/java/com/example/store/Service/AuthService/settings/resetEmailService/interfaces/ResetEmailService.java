package com.example.store.service.AuthService.settings.resetEmailService.interfaces;

import com.example.store.dto.authentification.UserResponse;
import com.example.store.dto.authentification.resetEmail.ResetEmailRequest;
import com.example.store.model.authentification.Role;
import com.example.store.model.authentification.User;

public interface ResetEmailService {

    boolean isAdmin(Role role);
    User findUserByEmail(String email);
    UserResponse resetEmail(ResetEmailRequest request);
   // UserResponse resetEmailAndResetCode(ResetEmailRequest request);
    String findEmailByName(String name);
}
