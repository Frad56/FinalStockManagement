package com.example.store.service.AuthService.settings.resetPasswordService.interfaces;

import com.example.store.dto.authentification.EmailRequestDTO;
import com.example.store.dto.authentification.OldPasswordVerification;
import com.example.store.dto.authentification.ResetPasswordRequest;
import com.example.store.model.authentification.Role;

public interface ResetPasswordService {

    boolean isAdmin(Role role);
    boolean existsByEmail(EmailRequestDTO dto);
     boolean existsByEmailAndNotAdmin(EmailRequestDTO dto);
    void changePassword(ResetPasswordRequest passwordRequest);
    void oldPasswordVerification(OldPasswordVerification oldPasswordVerification);

}
