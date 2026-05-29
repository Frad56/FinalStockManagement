package com.example.store.dto.authentification;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OldPasswordVerification {
    private String email;
    private String oldPassword;

}
