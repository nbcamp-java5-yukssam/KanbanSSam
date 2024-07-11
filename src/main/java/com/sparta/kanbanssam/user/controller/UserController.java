package com.sparta.kanbanssam.user.controller;

import com.sparta.kanbanssam.user.dto.UserSignUpRequestDto;
import com.sparta.kanbanssam.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    @PostMapping("")
    public ResponseEntity<?> signUp(@Valid @RequestBody UserSignUpRequestDto requestDto) {
        userService.signUp(requestDto);
        return ResponseEntity.ok("응답부 추후 수정");
    }

    //로그아웃 POST /users/logout

    //회원조회 GET /users
}
