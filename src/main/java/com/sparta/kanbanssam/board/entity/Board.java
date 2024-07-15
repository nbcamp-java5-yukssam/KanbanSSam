package com.sparta.kanbanssam.board.entity;

import com.sparta.kanbanssam.board.dto.BoardUpdateRequestDto;
import com.sparta.kanbanssam.common.enums.UserRole;
import com.sparta.kanbanssam.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table (name = "Board")
public class Board {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invite> invites;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String introduction;


    @Builder
    public Board(User user, String name, String introduction) {
        this.user = user;
        this.name = name;
        this.introduction = introduction;
    }
    public void updateBoard(BoardUpdateRequestDto requestDto) {
        this.name = requestDto.getName();
        this.introduction = requestDto.getIntroduction();
    }

}
