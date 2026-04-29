package com.example.library_management.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IssueRequestResponseDTO {

    private Long id;
    private String memberName;
    private String BookTitle;
    private String status;
    private LocalDate requestDate;
    private LocalDate returnDate;
}
