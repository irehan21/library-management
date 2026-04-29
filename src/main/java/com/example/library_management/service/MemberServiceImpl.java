package com.example.library_management.service;

import com.example.library_management.dto.MemberResponseDTO;
import com.example.library_management.dto.RegisterDTO;
import com.example.library_management.entity.Member;
import com.example.library_management.exception.ResourceNotFoundException;
import com.example.library_management.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {


    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private MemberResponseDTO toDTO(Member member) {
        MemberResponseDTO dto = new MemberResponseDTO();
        dto.setId(member.getId());
        dto.setName(member.getName());
        dto.setEmail(member.getEmail());
        dto.setRole(member.getRole().name());
        return dto;
    }

    @Override
    public MemberResponseDTO register(RegisterDTO dto) {
        Member member = new Member();
        member.setName(dto.getName());
        member.setPassword(passwordEncoder.encode(dto.getPassword()));
        member.setEmail(dto.getEmail());
        member.setRole(Member.Role.USER);
        return toDTO(memberRepository.save(member));
    }




    @Override
    public List<MemberResponseDTO> getAllMembers() {
        return memberRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public MemberResponseDTO getMemberByEmail(String email) {
        return toDTO(memberRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Member not found")));
    }

    @Override
    public MemberResponseDTO getMemberById(Long id) {
        return toDTO(memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member not found")));
    }
}
