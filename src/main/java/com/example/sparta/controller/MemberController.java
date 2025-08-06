package com.example.sparta.controller;

import com.example.sparta.dto.MemberDto;
import com.example.sparta.exception.CustomException;
import com.example.sparta.exception.ErrorCode;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberController {

    private final List<MemberDto> members = new ArrayList<>();

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@Valid @RequestBody MemberDto dto) {
        if (members.size() >= 10) {
            throw new CustomException(ErrorCode.USER_LIMIT_EXCEEDED);
        }
        members.add(dto);
        return ResponseEntity.ok("회원가입 성공");
    }

    @GetMapping("/check")
    public ResponseEntity<String> check(@RequestParam String name) {
        if (name == null || name.trim().length() < 2) {
            throw new CustomException(ErrorCode.INVALID_NAME);
        }
        boolean exists = members.stream().anyMatch(m -> m.getName().equals(name));
        if (exists) {
            throw new CustomException(ErrorCode.DUPLICATE_NAME);
        }
        return ResponseEntity.ok("사용 가능한 이름입니다.");
    }
}
