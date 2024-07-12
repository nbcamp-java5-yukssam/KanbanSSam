package com.sparta.kanbanssam.board.service;

import com.sparta.kanbanssam.board.dto.BoardRequestDto;
import com.sparta.kanbanssam.board.dto.BoardResponseDto;
import com.sparta.kanbanssam.board.entity.Board;
import com.sparta.kanbanssam.board.repository.BoardRepository;
import com.sparta.kanbanssam.common.enums.ErrorType;
import com.sparta.kanbanssam.common.exception.CustomException;
import com.sparta.kanbanssam.user.entity.User;
import com.sparta.kanbanssam.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userrepository;

    @Transactional
    public  BoardResponseDto createBoard(BoardRequestDto requestDto, User manager) {
//        user = userrepository.findById().orElseThrow(()
//                -> new CustomException(ErrorType.USER_NOT_FOUND));
//        if(userRole.enum)
        // TODO : UserID 확인후 userRole 이 Manager 임을 검증하는 if문이 들어와야함

        Board board = Board.builder()
                .manager(manager)
                .name(requestDto.getName())
                .introduction(requestDto.getIntroduction())
                .build();

        boardRepository.save(board);

        return new BoardResponseDto(board);
    }

}
