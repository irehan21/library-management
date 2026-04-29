package com.example.library_management.repository;

import com.example.library_management.entity.IssueRequest;
import com.example.library_management.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRequestRepository extends JpaRepository<IssueRequest, Long> {

    List<IssueRequest> findByMember(Member member);
}
