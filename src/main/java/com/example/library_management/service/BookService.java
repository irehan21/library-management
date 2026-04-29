package com.example.library_management.service;

import com.example.library_management.dto.BookDTO;
import com.example.library_management.dto.BookResponseDTO;
import com.example.library_management.entity.Book;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface BookService {

    BookResponseDTO addBook(BookDTO dto);
    List<BookResponseDTO> getAllBooks();
    BookResponseDTO getBookById(Long id);
    BookResponseDTO updateBook(Long id, BookDTO dto);
    void deleteBook(Long id);
    List<BookResponseDTO> searchBooks(String title);

}
