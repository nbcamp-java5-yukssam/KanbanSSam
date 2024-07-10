package com.sparta.kanbanssam.board.controller;


import com.sparta.kanbanssam.board.dto.BoardRequestDto;
import com.sparta.kanbanssam.board.dto.BoardResponseDto;
import com.sparta.kanbanssam.board.service.BoardService;
import com.sparta.kanbanssam.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/user/{userId}/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardservice;

    @ResponseBody
    @PostMapping
    public ResponseEntity<?> createBoard(@RequestBody BoardRequestDto requestDto
                                      //AuthenticationPrincipal UserDetails userdeatils,
//                                      BindingResult bindingResult
                                    ) {
        User user = User.builder().id(1L).build(); // User 객체 ID를 일단 1로고정
        BoardResponseDto responseDto = boardservice.createBoard(requestDto, user);

        return ResponseEntity.ok(responseDto);
        }
    }
