package com.sparta.kanbanssam.board.dto;

import com.sparta.kanbanssam.board.entity.Board;
import com.sparta.kanbanssam.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class BoardInviteResponseDto {
    private String boardName;
    private String name;
    private String email;

    private BoardInviteResponseDto(User user, Board board) {
        this.boardName = board.getName();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public BoardInviteResponseDto(Board board) {
        this.boardName = board.getName();
        this.name = board.getUser().getName();
        this.email = board.getUser().getEmail();
    }
}
