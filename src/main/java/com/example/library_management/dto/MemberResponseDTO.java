package com.example.library_management.dto;

import lombok.Data;

@Data
public class MemberResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String role;
}
