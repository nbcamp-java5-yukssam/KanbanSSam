package com.sparta.kanbanssam.board.dto;

import com.sparta.kanbanssam.board.entity.Board;
import com.sparta.kanbanssam.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class BoardInviteRequsetDto {

    private User user;
    private Board board;

    private BoardInviteRequsetDto(User user, Board board) {
        this.user = user;
        this.board = board;
    }
}
