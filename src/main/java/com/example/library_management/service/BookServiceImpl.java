package com.example.library_management.service;

import com.example.library_management.dto.BookDTO;
import com.example.library_management.dto.BookResponseDTO;
import com.example.library_management.entity.Author;
import com.example.library_management.entity.Book;
import com.example.library_management.exception.ResourceNotFoundException;
import com.example.library_management.repository.AuthorRepository;
import com.example.library_management.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    private BookResponseDTO toDto(Book book) {
        BookResponseDTO dto = new BookResponseDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setIsbn(book.getIsbn());
        dto.setGenre(book.getGenre());
        dto.setAvailable(book.isAvailable());
        dto.setAuthorName(book.getAuthor() != null ? book.getAuthor().getName() : "Unknown");
        return dto;
    }

    @Override
    public BookResponseDTO addBook(BookDTO dto){
        Author author = authorRepository.findById(dto.getAuthorId()).orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setGenre(dto.getGenre());
        book.setAuthor(author);

        return toDto(bookRepository.save(book));

    }


    @Override
    public List<BookResponseDTO> getAllBooks() {
        return bookRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public BookResponseDTO getBookById(Long id) {
        return toDto(bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found")));
    }

    @Override
    public BookResponseDTO updateBook(Long id, BookDTO dto) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setGenre(dto.getGenre());
        return toDto(bookRepository.save(book));
    }

    @Override
    public void deleteBook(Long id) {
         bookRepository.deleteById(id);
    }

    @Override
    public List<BookResponseDTO> searchBooks(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title).stream().map(this::toDto).toList();

    }
}
