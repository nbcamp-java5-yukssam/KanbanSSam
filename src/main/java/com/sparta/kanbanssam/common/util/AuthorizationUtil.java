package com.sparta.kanbanssam.common.util;

import com.sparta.kanbanssam.board.entity.Board;
import com.sparta.kanbanssam.board.repository.BoardRepository;
import com.sparta.kanbanssam.common.enums.ErrorType;
import com.sparta.kanbanssam.common.exception.CustomException;
import com.sparta.kanbanssam.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationUtil {

    private final BoardRepository boardRepository;

    @Autowired
    public AuthorizationUtil(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board validateUserIsBoardManager(Long boardId, User user) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new CustomException(ErrorType.NOT_FOUND_BOARD));

        if (!board.getUser().getId().equals(user.getId())) {
            throw new CustomException(ErrorType.NO_AUTHENTICATION);
        }
        return board;
    }
}