package com.sparta.kanbanssam.column.controller;

import com.sparta.kanbanssam.board.entity.Board;
import com.sparta.kanbanssam.board.service.BoardService;
import com.sparta.kanbanssam.card.dto.CardRequestDto;
import com.sparta.kanbanssam.card.dto.CardResponseDto;
import com.sparta.kanbanssam.column.dto.ColumnRequestDto;
import com.sparta.kanbanssam.column.dto.ColumnResponseDto;
import com.sparta.kanbanssam.column.service.ColumnService;
import com.sparta.kanbanssam.common.util.AuthorizationUtil;
import com.sparta.kanbanssam.security.UserDetailsImpl;
import com.sparta.kanbanssam.user.entity.User;
import com.sparta.kanbanssam.user.repository.UserRepository;
import com.sparta.kanbanssam.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class ColumnController {
    private final ColumnService columnService;
    private final AuthorizationUtil authorizationUtil;

    /**
     * 컬럼 생성
     * @param boardId 보드 ID
     * @param requestDto 컬럼 생성 정보
     * @return 컬럼 정보
     */
    @ResponseBody
    @PostMapping("/{boardId}/columns")
    public ResponseEntity<?> createColumn(@PathVariable Long boardId, @RequestBody ColumnRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails){

        // 로그인된 사용자 정보 가져오기
        User user = userDetails.getUser();

        // 로그인된 사용자의 accountId 가져오기
        //String accountId = userDetails.getUser().getAccountId();

        /*// 사용자 정보를 데이터베이스에서 조회
        User user = userRepository.findByAccountId(accountId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with accountId: " + accountId));*/

        // 사용자가 컬럼을 생성할 권한이 있는지 확인(컬럼을 생성하고자하는 보드를 만든 사용자인지 확인)
        //boardService.validateUserIsBoardManager(boardId, user);

        // 사용자가 보드의 관리자임을 확인
        Board board = authorizationUtil.validateUserIsBoardManager(boardId, user);

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

        // 로그인된 사용자의 accountId 가져오기
        //String accountId = userDetails.getUser().getAccountId();

        /*// 사용자 정보를 데이터베이스에서 조회
        User user = userRepository.findByAccountId(accountId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with accountId: " + accountId));
*/
        // 사용자가 컬럼을 수정할 권한이 있는지 확인
        //boardService.validateUserIsBoardManager(boardId, user);

        // 사용자가 보드의 관리자임을 확인
        Board board = authorizationUtil.validateUserIsBoardManager(boardId, user);

        // 권한 확인 후 컬럼 생성
        ColumnResponseDto responseDto = columnService.updateColumn(columnId, requestDto, user);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/columns/view")
    public String columnView() {
        return "/column/column";
    }
}
