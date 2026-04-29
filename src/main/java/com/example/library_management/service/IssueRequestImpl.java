package com.example.library_management.service;

import com.example.library_management.dto.IssueRequestResponseDTO;
import com.example.library_management.entity.Book;
import com.example.library_management.entity.IssueRequest;
import com.example.library_management.entity.Member;
import com.example.library_management.exception.ResourceNotFoundException;
import com.example.library_management.repository.BookRepository;
import com.example.library_management.repository.IssueRequestRepository;
import com.example.library_management.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueRequestImpl implements IssueRequestService {

    private final IssueRequestRepository issueRequestRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    private IssueRequestResponseDTO toDTO(IssueRequest request) {
        IssueRequestResponseDTO dto = new IssueRequestResponseDTO();
        dto.setId(request.getId());
        dto.setMemberName(request.getMember().getName());
        dto.setBookTitle(request.getBook().getTitle());
        dto.setStatus(request.getStatus().name());
        dto.setRequestDate(request.getRequestDate());
        dto.setReturnDate(request.getReturnDate());
        return dto;
    }

    @Override
    public IssueRequestResponseDTO requestBook(String email, Long bookId){
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Member not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        if (!book.isAvailable()){
            throw new ResourceNotFoundException("Book is not available");
        }

        IssueRequest issueRequest = new IssueRequest();
        issueRequest.setMember(member);
        issueRequest.setBook(book);
        issueRequest.setStatus(IssueRequest.Status.PENDING);
        return toDTO(issueRequestRepository.save(issueRequest));
    }

    @Override
    public IssueRequestResponseDTO approveRequest(Long requestId) {
        IssueRequest request = issueRequestRepository.findById(requestId).orElseThrow(() -> new ResourceNotFoundException("Request not found"));
        request.setStatus(IssueRequest.Status.APPROVED);
        request.getBook().setAvailable(false);
        bookRepository.save(request.getBook());

        return toDTO(issueRequestRepository.save(request));
    }

    @Override
    public IssueRequestResponseDTO rejectRequest(Long requestId) {
        IssueRequest request = issueRequestRepository.findById(requestId).orElseThrow(() -> new ResourceNotFoundException("Request not found"));
        request.setStatus(IssueRequest.Status.REJECTED);
        return toDTO(issueRequestRepository.save(request));
    }

    @Override
    public IssueRequestResponseDTO returnBook(Long requestId) {
        IssueRequest request = issueRequestRepository.findById(requestId).orElseThrow(() -> new ResourceNotFoundException("Request not found"));
        request.setStatus(IssueRequest.Status.RETURNED);
        request.getBook().setAvailable(true);
        request.setReturnDate(LocalDate.now());
        bookRepository.save(request.getBook());
        return toDTO(issueRequestRepository.save(request));
    }

    @Override
    public List<IssueRequestResponseDTO> getAllRequests() {
        return issueRequestRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public List<IssueRequestResponseDTO> getRequestsByMember(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Member not found"));
        return issueRequestRepository.findByMember(member).stream().map(this::toDTO).toList();
    }


}
