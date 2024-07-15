package com.sparta.kanbanssam.board.entity;

import com.sparta.kanbanssam.board.dto.BoardInviteRequsetDto;
import com.sparta.kanbanssam.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "Invite")
public class Invite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public void inviteUser(BoardInviteRequsetDto requsetDto) {
        user = requsetDto.getUser();
        board = requsetDto.getBoard();
    }
}
