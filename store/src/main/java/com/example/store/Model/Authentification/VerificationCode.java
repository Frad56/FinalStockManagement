package com.example.store.model.authentification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class VerificationCode {
    private String code;
    private String email;

}
