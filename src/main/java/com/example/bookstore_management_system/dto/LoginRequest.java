package com.example.bookstore_management_system.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
