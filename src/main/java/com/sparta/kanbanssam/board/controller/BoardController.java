package com.sparta.kanbanssam.board.controller;


import com.sparta.kanbanssam.board.dto.BoardRequestDto;
import com.sparta.kanbanssam.board.dto.BoardResponseDto;
import com.sparta.kanbanssam.board.dto.BoardUpdateRequestDto;
import com.sparta.kanbanssam.board.dto.BoardUpdateResponseDto;
import com.sparta.kanbanssam.board.entity.Board;
import com.sparta.kanbanssam.board.service.BoardService;
import com.sparta.kanbanssam.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardservice;

    @ResponseBody
    @PostMapping
    public ResponseEntity<?> createBoard(@RequestBody BoardRequestDto requestDto
                                      //AuthenticationPrincipal UserDetails userdeatils,
//                                      BindingResult bindingResult
                                    ) {
        User manager = User.builder().id(1L).build(); // User 객체 ID를 일단 1로고정
        BoardResponseDto responseDto = boardservice.createBoard(requestDto, manager);

        return ResponseEntity.ok(responseDto);
        }

    @PutMapping("/{boardId}")
    public ResponseEntity<?> findAllBoard(//AuthenticationPrincipal UserDetails userdeatils
                                          @RequestBody BoardUpdateRequestDto requestDto,
                                          @PathVariable Long boardId) {
        Board board = boardservice.updateBoard(boardId, requestDto, );/*userDeails*/
        BoardUpdateResponseDto reponseDto = new BoardUpdateResponseDto(board);

        return ResponseEntity.ok(reponseDto);
    }


}
