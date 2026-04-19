package com.example.store.DTO.authentification;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserResponse {
    String username;
    String userEmail;
}
