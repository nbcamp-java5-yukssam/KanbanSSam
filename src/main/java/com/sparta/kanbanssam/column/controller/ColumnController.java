package com.sparta.kanbanssam.column.controller;

import com.sparta.kanbanssam.board.entity.Board;
import com.sparta.kanbanssam.card.dto.CardRequestDto;
import com.sparta.kanbanssam.card.dto.CardResponseDto;
import com.sparta.kanbanssam.column.dto.ColumnRequestDto;
import com.sparta.kanbanssam.column.dto.ColumnResponseDto;
import com.sparta.kanbanssam.column.service.ColumnService;
import com.sparta.kanbanssam.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class ColumnController {
    private final ColumnService columnService;

    /**
     * 컬럼 생성
     * @param boardId 보드 ID
     * @param requestDto 컬럼 생성 정보
     * @return 컬럼 정보
     */
    @ResponseBody
    @PostMapping("/{boardId}/columns")
    public ResponseEntity<?> createColumn(@PathVariable Long boardId, @RequestBody ColumnRequestDto requestDto){
        // todo : security 구현 완료 시 userDetails 로 수정
        User user = User.builder().id(1L).build();
        ColumnResponseDto responseDto = columnService.createColum(boardId, requestDto, user);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * 컬럼 수정
     * @param boardId 보드 ID
     * @param columnId 컬럼 ID
     * @param requestDto 컬럼 수정 정보
     * @return 카드 정보
     */
    @ResponseBody
    @PutMapping("/{boardId}/columns/{columnId}")
    public ResponseEntity<?> updateCard(
            @PathVariable Long boardId,
            @PathVariable Long columnId,
            @Valid @RequestBody ColumnRequestDto requestDto) {
        // todo : security 구현 완료 시 userDetails 로 수정
        User user = User.builder().id(1L).build();
        ColumnResponseDto responseDto = columnService.updateColumn(columnId, requestDto, user);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/columns/view")
    public String columnView() {
        return "/column/column";
    }
}
