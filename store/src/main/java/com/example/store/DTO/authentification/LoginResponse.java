package com.example.store.dto.authentification;


import com.example.store.model.authentification.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private Role  role;
    private Boolean isEmailChanged;
}
