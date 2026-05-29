package com.example.store.dto.authentification;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResetPasswordRequest {
        private String email;
        private String newPassword;
        private String code;

}

