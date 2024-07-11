package com.sparta.kanbanssam.column.service;

import com.sparta.kanbanssam.board.entity.Board;
import com.sparta.kanbanssam.board.repository.BoardRepository;
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
}
