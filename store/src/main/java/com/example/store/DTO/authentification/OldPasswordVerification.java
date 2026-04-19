package com.example.store.DTO.authentification;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OldPasswordVerification {
    private String email;
    private String oldPassword;

}
