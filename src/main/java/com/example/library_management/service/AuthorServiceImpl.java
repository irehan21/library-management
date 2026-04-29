package com.example.library_management.service;

import com.example.library_management.dto.AuthorDTO;
import com.example.library_management.dto.AuthorResponseDTO;
import com.example.library_management.entity.Author;
import com.example.library_management.exception.ResourceNotFoundException;
import com.example.library_management.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    // Entity → ResponseDTO convert karna
    private AuthorResponseDTO toDTO(Author author) {
        AuthorResponseDTO dto = new AuthorResponseDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        dto.setBio(author.getBio());
        return dto;
    }

    @Override
    public AuthorResponseDTO addAuthor(AuthorDTO dto) {
        Author author = new Author();
        author.setName(dto.getName());
        author.setBio(dto.getBio());
        return toDTO(authorRepository.save(author));
    }

    @Override
    public List<AuthorResponseDTO> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public AuthorResponseDTO getAuthorById(Long id) {
        return toDTO(authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found")));
    }

    @Override
    public AuthorResponseDTO updateAuthor(Long id, AuthorDTO dto) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        author.setName(dto.getName());
        author.setBio(dto.getBio());
        return toDTO(authorRepository.save(author));
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
