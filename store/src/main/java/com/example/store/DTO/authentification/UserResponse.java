package com.example.store.dto.authentification;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserResponse {
    String username;
    String userEmail;
}
