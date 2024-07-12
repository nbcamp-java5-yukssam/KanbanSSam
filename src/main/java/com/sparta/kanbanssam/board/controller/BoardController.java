package com.sparta.kanbanssam.board.controller;


import com.sparta.kanbanssam.board.dto.BoardRequestDto;
import com.sparta.kanbanssam.board.dto.BoardResponseDto;
import com.sparta.kanbanssam.board.service.BoardService;
import com.sparta.kanbanssam.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardservice;

    @ResponseBody
    @PostMapping
    public ResponseEntity<?> createBoard(
            @RequestBody BoardRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        BoardResponseDto responseDto = boardservice.createBoard(requestDto, userDetails.getUser());

        return ResponseEntity.ok(responseDto);
        }
    }
