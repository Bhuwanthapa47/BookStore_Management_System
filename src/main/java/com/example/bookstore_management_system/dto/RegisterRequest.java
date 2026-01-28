package com.example.bookstore_management_system.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String role; // CUSTOMER or ADMIN
}
