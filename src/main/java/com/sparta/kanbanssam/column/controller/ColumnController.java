package com.sparta.kanbanssam.column.controller;

import com.sparta.kanbanssam.board.entity.Board;
import com.sparta.kanbanssam.card.dto.CardRequestDto;
import com.sparta.kanbanssam.card.dto.CardResponseDto;
import com.sparta.kanbanssam.column.dto.ColumnRequestDto;
import com.sparta.kanbanssam.column.dto.ColumnResponseDto;
import com.sparta.kanbanssam.column.service.ColumnService;
import com.sparta.kanbanssam.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class ColumnController {
    private final ColumnService columnService;

    @ResponseBody
    @PostMapping("/{boardId}/column")
    public ResponseEntity<?> createColumn(@PathVariable Long boardId, @RequestBody ColumnRequestDto requestDto){
        // todo : secutity 구현 완료 시 userDetails 로 수정
        User user = User.builder().id(1L).build();
        ColumnResponseDto responseDto = columnService.createColum(boardId, requestDto, user);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/column/view")
    public String columnView() {
        return "/column/column";
    }
}
