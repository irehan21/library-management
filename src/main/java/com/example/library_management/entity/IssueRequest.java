package com.example.library_management.entity;

import ch.qos.logback.core.status.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "issue_requests")
public class IssueRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    private LocalDate requestDate = LocalDate.now();

    private LocalDate returnDate = LocalDate.now();

    public enum Status {
        PENDING, APPROVED, REJECTED, RETURNED
    }
}
