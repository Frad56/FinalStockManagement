package com.example.store.DTO.authentification;


import com.example.store.Model.Authentification.Role;
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
