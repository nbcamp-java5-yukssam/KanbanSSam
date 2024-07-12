package com.sparta.kanbanssam.column.controller;

import com.sparta.kanbanssam.column.dto.ColumnRequestDto;
import com.sparta.kanbanssam.column.dto.ColumnResponseDto;
import com.sparta.kanbanssam.column.service.ColumnService;
import com.sparta.kanbanssam.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class ColumnController {
    private final ColumnService columnService;

    @ResponseBody
    @PostMapping("/{boardId}/columns")
    public ResponseEntity<?> createColumn(@PathVariable Long boardId, @RequestBody ColumnRequestDto requestDto){
        // todo : secutity 구현 완료 시 userDetails 로 수정
       // User user = User.builder().build();
        ColumnResponseDto responseDto = columnService.createColum(boardId, requestDto, user);
    public ResponseEntity<?> createColumn(
            @PathVariable Long boardId,
            @RequestBody ColumnRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails){
        ColumnResponseDto responseDto = columnService.createColum(boardId, requestDto, userDetails.getUser());
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/columns/view")
    public String columnView() {
        return "/column/column";
    }
}
