package com.example.library_management.controller;

import com.example.library_management.dto.LoginDTO;
import com.example.library_management.entity.Member;
import com.example.library_management.repository.MemberRepository;
import com.example.library_management.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO dto) {

        // Email se member dhundho
        Member member = memberRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        // Password match karo
        if (!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Token generate karo
        String token = jwtUtil.generateToken(
                member.getEmail(),
                member.getRole().name()
        );

        return ResponseEntity.ok(Map.of(
                "token", token,
                "role", member.getRole().name(),
                "name", member.getName()
        ));
    }
}