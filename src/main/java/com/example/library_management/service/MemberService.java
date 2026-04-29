package com.example.library_management.service;
import com.example.library_management.dto.MemberResponseDTO;
import com.example.library_management.dto.RegisterDTO;
import com.example.library_management.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface MemberService {

    MemberResponseDTO register(RegisterDTO dto);
    List<MemberResponseDTO> getAllMembers();
    MemberResponseDTO getMemberById(Long id);
    MemberResponseDTO getMemberByEmail(String email);


}
