package com.sparta.kanbanssam.board.controller;


import com.sparta.kanbanssam.board.dto.BoardRequestDto;
import com.sparta.kanbanssam.board.dto.BoardResponseDto;
import com.sparta.kanbanssam.board.dto.BoardUpdateRequestDto;
import com.sparta.kanbanssam.board.dto.BoardUpdateResponseDto;
import com.sparta.kanbanssam.board.entity.Board;
import com.sparta.kanbanssam.board.service.BoardService;
import com.sparta.kanbanssam.security.UserDetailsImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardservice;

    @ResponseBody
    @PostMapping
    public ResponseEntity<?> createBoard(@RequestBody @Valid  BoardRequestDto requestDto,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails
                                    ) {
    public ResponseEntity<?> createBoard(
            @RequestBody BoardRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        BoardResponseDto responseDto = boardservice.createBoard(requestDto, userDetails.getUser());

        return ResponseEntity.ok(responseDto);
        }

        //상품수정
    @Transactional
    @PutMapping("/{boardId}")
    public ResponseEntity<?> updateBoard(@RequestBody BoardUpdateRequestDto requestDto,
                                          @PathVariable Long boardId,
                                          @AuthenticationPrincipal UserDetailsImpl userdeatils) {
        Board board = boardservice.updateBoard(boardId, requestDto, userdeatils.getUser());
        BoardUpdateResponseDto reponseDto = new BoardUpdateResponseDto(board);

        return ResponseEntity.ok(reponseDto);
    }


}
