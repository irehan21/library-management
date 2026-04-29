package com.example.library_management.controller;

import com.example.library_management.dto.IssueRequestResponseDTO;
import com.example.library_management.service.IssueRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/issues")
@RequiredArgsConstructor
public class IssueRequestController {

    private final IssueRequestService issueRequestService;

    // Member book request karta hai
    @PostMapping("/request")

    public ResponseEntity<IssueRequestResponseDTO> requestBook(@RequestParam Long bookId, Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(issueRequestService.requestBook(email, bookId));
    }

    // ADMIN approve karta hai
    @PutMapping("/approve/{requestId}")
    public ResponseEntity<IssueRequestResponseDTO> approveRequest(@PathVariable Long requestId) {
        return ResponseEntity.ok(issueRequestService.approveRequest(requestId));
    }

    // ADMIN reject karta hai
    @PutMapping("/reject/{requestId}")
    public ResponseEntity<IssueRequestResponseDTO> rejectRequest(@PathVariable Long requestId) {
        return ResponseEntity.ok(issueRequestService.rejectRequest(requestId));
    }

    // Member return karta hai
    @PutMapping("/return/{requestId}")
    public ResponseEntity<IssueRequestResponseDTO> returnBook(@PathVariable Long requestId) {
        return ResponseEntity.ok(issueRequestService.returnBook(requestId));
    }

    // Saari requests — ADMIN
//    @GetMapping
//    public ResponseEntity<List<IssueRequestResponseDTO>> getAllRequests() {
//        return ResponseEntity.ok(issueRequestService.getAllRequests());
//    }

    // Member ki apni requests
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<IssueRequestResponseDTO>> getRequestsByMember(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(issueRequestService.getRequestsByMember(email));
    }

    // Saari requests (ADMIN)
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<IssueRequestResponseDTO>> getAllRequests() {
        return ResponseEntity.ok(issueRequestService.getAllRequests());
    }

    // Member ki apni requests (USER)
    @GetMapping("/my-requests")
    public ResponseEntity<List<IssueRequestResponseDTO>> getMyRequests(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(issueRequestService.getRequestsByMember(email));
    }
}
