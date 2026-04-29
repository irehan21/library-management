package com.example.library_management.dto;

import lombok.Data;

@Data
public class BookResponseDTO {

    private Long id;
    private String title;
    private String authorName;
    private String isbn;
    private String genre;
    private boolean available;

}
