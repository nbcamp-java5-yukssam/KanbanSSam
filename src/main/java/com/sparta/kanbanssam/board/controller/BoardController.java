package com.sparta.kanbanssam.board.controller;


import com.sparta.kanbanssam.board.dto.*;
import com.sparta.kanbanssam.board.entity.Board;
import com.sparta.kanbanssam.board.entity.Invite;
import com.sparta.kanbanssam.board.service.BoardService;
import com.sparta.kanbanssam.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardservice;

    @ResponseBody
    @PostMapping
    public ResponseEntity<?> createBoard(@RequestBody @Valid BoardRequestDto requestDto,
                                         @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        BoardResponseDto responseDto = boardservice.createBoard(requestDto, userDetails.getUser());
        return ResponseEntity.ok(responseDto);
    }

    //   보드 전체 조회
    @GetMapping
    public ResponseEntity<?> findAllBoard(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<BoardResponseDto> responseDto = boardservice.findAllBoard(userDetails.getUser());
        return ResponseEntity.ok(responseDto);
    }

    //보드 선택 조회
    //보드 선택 조회
    @GetMapping("/{boardId}")
    public ResponseEntity<?> finaBoard(@PathVariable Long boardId,
                                       Invite invite,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Board board = boardservice.findBoard(boardId, userDetails.getUser(), invite);
        BoardResponseDto responseDto = new BoardResponseDto(board);
        return ResponseEntity.ok(responseDto);
    }

//    보드 초대
    @ResponseBody
    @PostMapping("/{boardId}/invite")
    public ResponseEntity<?> inviteUser(@PathVariable Long boardId,
                                        @RequestBody BoardInviteRequsetDto requsetDto,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Board board = boardservice.inviteUserToBoard(boardId, requsetDto, userDetails.getUser());
        BoardResponseDto requestDto = new BoardResponseDto(board);
        return ResponseEntity.ok(requestDto);
    }

    //상품수정
    @PutMapping("/{boardId}")
    public ResponseEntity<?> updateBoard(@RequestBody BoardUpdateRequestDto requestDto,
                                         @PathVariable Long boardId,
                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Board board = boardservice.updateBoard(boardId, requestDto, userDetails.getUser());
        BoardUpdateResponseDto reponseDto = new BoardUpdateResponseDto(board);

        return ResponseEntity.ok(reponseDto);
    }

    //보드 삭제
    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deleteBoard(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                         @PathVariable Long boardId) {
        boardservice.deleteBoard(boardId, userDetails.getUser());
        return ResponseEntity.ok().body("보드가 삭제되었습니다.");
    }
}
