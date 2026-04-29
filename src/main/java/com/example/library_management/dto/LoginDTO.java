package com.example.library_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {

    @NotBlank
    @Email(message = "Valid email required")
    private String email;

    @NotBlank
    private String password;
}
