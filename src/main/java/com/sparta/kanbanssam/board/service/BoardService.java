package com.sparta.kanbanssam.board.service;

import com.sparta.kanbanssam.board.dto.BoardRequestDto;
import com.sparta.kanbanssam.board.dto.BoardResponseDto;
import com.sparta.kanbanssam.board.dto.BoardUpdateRequestDto;
import com.sparta.kanbanssam.board.dto.BoardUpdateResponseDto;
import com.sparta.kanbanssam.board.entity.Board;
import com.sparta.kanbanssam.board.repository.BoardRepository;
import com.sparta.kanbanssam.common.enums.ErrorType;
import com.sparta.kanbanssam.common.enums.UserRole;
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
    private final UserRepository userRepository;

    @Transactional
    public  BoardResponseDto createBoard(BoardRequestDto requestDto, User user) {
        user = userRepository.findByAccountId(user.getAccountId()).orElseThrow(()
                -> new CustomException(ErrorType.USER_NOT_FOUND));

        //유저 Role이 매니저인지 검증하는 로직
        checkUserRole(user);

        Board board = Board.builder()
                .name(requestDto.getName())
                .introduction(requestDto.getIntroduction())
                .manager(user)
                .build();

        boardRepository.save(board);

        return new BoardResponseDto(board);
    }

    // 보드수정로직
    public Board updateBoard(Long boardId, BoardUpdateRequestDto requestDto, User user) {
        //보드 ID 검색 후 Null 값이라면 Exception 발생
        Board board = boardRepository.findById(boardId).orElseThrow(()
                -> new CustomException(ErrorType.BOARD_NOT_FOUND));
        //유저 Role이 매니저인지 검증하는 로직
        checkUserRole(user);
        //보드에서 가져온 ID 와 User 의 ID가 다르다면 Exception 발생
        if (!board.getId().equals(user.getId())) {
            new CustomException(ErrorType.BOARD_ACCESS_FORBIDDEN);
        }
        // 수정
        board.updateBoard(requestDto);
        return board;
    }


    //UserRole 이 Manager 인지 검증하는 로직
    private void checkUserRole(User user) {
        if (user.getUserRole().equals(UserRole.USER)){
            throw new CustomException(ErrorType.NO_AUTHENTICATION);}
        }
    }

