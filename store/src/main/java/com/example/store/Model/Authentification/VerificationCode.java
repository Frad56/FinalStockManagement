package com.example.store.Model.Authentification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class VerificationCode {
    private String code;
    private String email;

}
