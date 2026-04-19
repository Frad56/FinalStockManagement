package com.example.store.DTO.authentification.resetEmail;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResetEmailRequest {
    private  String oldEmail;
    private  String newEmail;
    private String code;
}
