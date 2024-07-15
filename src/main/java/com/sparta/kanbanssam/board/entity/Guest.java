package com.sparta.kanbanssam.board.entity;

import com.sparta.kanbanssam.board.dto.BoardInviteRequsetDto;
import com.sparta.kanbanssam.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Guest")
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Email
    @Column(nullable = false)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;


    public Guest(User user, Board board) {
        this.user = user;
        this.board = board;
        this.email = user.getEmail();
    }
}
