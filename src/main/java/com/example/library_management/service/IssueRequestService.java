package com.example.library_management.service;

import com.example.library_management.dto.IssueRequestResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IssueRequestService {

    IssueRequestResponseDTO requestBook(String email, Long bookId );
    IssueRequestResponseDTO approveRequest(Long requestId );
    IssueRequestResponseDTO rejectRequest(Long requestId );
    IssueRequestResponseDTO returnBook(Long requestId );
    List<IssueRequestResponseDTO> getAllRequests();
    List<IssueRequestResponseDTO> getRequestsByMember(String email );

}
