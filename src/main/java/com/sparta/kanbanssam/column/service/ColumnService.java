package com.sparta.kanbanssam.column.service;

import com.sparta.kanbanssam.board.entity.Board;
import com.sparta.kanbanssam.board.repository.BoardRepository;
import com.sparta.kanbanssam.card.dto.CardResponseDto;
import com.sparta.kanbanssam.card.entity.Card;
import com.sparta.kanbanssam.column.dto.ColumnRequestDto;
import com.sparta.kanbanssam.column.dto.ColumnResponseDto;
import com.sparta.kanbanssam.column.entity.Columns;
import com.sparta.kanbanssam.column.repository.ColumnRepository;
import com.sparta.kanbanssam.common.enums.ErrorType;
import com.sparta.kanbanssam.common.exception.CustomException;
import com.sparta.kanbanssam.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ColumnService {

    private final ColumnRepository columnRepository;
    private final BoardRepository boardRepository;

    /**
     * 컬럼 생성
     * @param boardId 보드 ID
     * @param requestDto 컬럼 생성 정보
     * @param user 회원 정보
     * @return 컬럼 정보
     */
    @Transactional
    public ColumnResponseDto createColum(Long boardId, ColumnRequestDto requestDto, User user) {
        // todo : BoardService에 Board 엔티티 조회 메서드 생성 시 수정
        Board board = boardRepository.findById(boardId).orElseThrow(
                ()-> new CustomException(ErrorType.BOARD_NOT_FOUND));

        // 해당 보드에 존재하는 컬럼 총 개수
        Long columnCnt = columnRepository.countAllByBoardId(boardId);

        Columns column = Columns.builder()
                .board(board)
                .name(requestDto.getName())
                .orders(columnCnt + 1)
                .build();

        Columns saveColumn = columnRepository.save(column);

        return new ColumnResponseDto(saveColumn);
    }


    /**
     * 컬럼 수정
     * @param columnId 컬럼 ID
     * @param requestDto 컬럼 수정 정보
     * @param user 회원 정보
     * @return 컬럼 정보
     */
    @Transactional
    public ColumnResponseDto updateColumn(Long columnId, ColumnRequestDto requestDto, User user) {
        Columns column = getColumn(columnId);

        // 카드 작성자가 아닐 경우 예외처리
        // todo : User 구현 완료 시 UserRole 권한 체크 로직도 추가
        column.validateAuthority(user);
        column.update(requestDto);

        return new ColumnResponseDto(column);
    }

    /**
     * Id로 Columns 엔티티 찾기
     * @param columnId 컬럼 ID
     * @return 컬럼 정보
     */
    private Columns getColumn(Long columnId) {
        return columnRepository.findById(columnId)
                .orElseThrow(()-> new CustomException(ErrorType.COLUMN_NOT_FOUND));
    }

}
