package com.example.library_management.service;

import com.example.library_management.dto.AuthorDTO;
import com.example.library_management.dto.AuthorResponseDTO;
import java.util.List;

public interface AuthorService {
    AuthorResponseDTO addAuthor(AuthorDTO dto);
    List<AuthorResponseDTO> getAllAuthors();
    AuthorResponseDTO getAuthorById(Long id);
    AuthorResponseDTO updateAuthor(Long id, AuthorDTO dto);
    void deleteAuthor(Long id);
}