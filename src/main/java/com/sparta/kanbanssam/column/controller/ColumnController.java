package com.sparta.kanbanssam.column.controller;

import com.sparta.kanbanssam.board.entity.Board;

import com.sparta.kanbanssam.column.dto.ColumnRequestDto;
import com.sparta.kanbanssam.column.dto.ColumnResponseDto;
import com.sparta.kanbanssam.column.service.ColumnService;
import com.sparta.kanbanssam.common.authorization.AuthorizationService;
import com.sparta.kanbanssam.security.UserDetailsImpl;
import com.sparta.kanbanssam.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
@Slf4j
public class ColumnController {
    private final ColumnService columnService;
    private final AuthorizationService authorizationService;

    /**
     * 컬럼 생성
     * @param boardId 보드 ID
     * @param requestDto 컬럼 생성 정보
     * @return 컬럼 정보
     */
    @ResponseBody
    @PostMapping("/{boardId}/columns")
    public ResponseEntity<?> createColumn(
            @PathVariable Long boardId,
            @RequestBody ColumnRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails){

        // 로그인된 사용자 정보 가져오기
        User user = userDetails.getUser();

        // 권한 확인 후 컬럼 생성
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
            @Valid @RequestBody ColumnRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        // 로그인된 사용자 정보 가져오기
        User user = userDetails.getUser();

        // 권한 확인 후 컬럼 생성
        ColumnResponseDto responseDto = columnService.updateColumn(columnId, requestDto, user);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/columns/view")
    public String columnView() {
        return "/column/column";
    }
}
