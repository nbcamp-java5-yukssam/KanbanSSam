package com.sparta.kanbanssam.board.entity;


import com.sparta.kanbanssam.board.dto.BoardUpdateRequestDto;
import com.sparta.kanbanssam.card.entity.Card;
import com.sparta.kanbanssam.column.entity.Columns;
import com.sparta.kanbanssam.common.enums.ErrorType;
import com.sparta.kanbanssam.common.enums.UserRole;
import com.sparta.kanbanssam.common.exception.CustomException;
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

    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private User manager;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String introduction;

    @OneToMany(mappedBy = "board", orphanRemoval = true)
    private List<Columns> columnsList;

    @OneToMany(mappedBy = "board", orphanRemoval = true)
    private List<Card> cardList;

    @Builder
    public Board(Long id, User manager, String name, String introduction) {
        this.id = id;
        this.manager = manager;
        this.name = name;
        this.introduction = introduction;
    }

    public void updateBoard(BoardUpdateRequestDto requestDto) {
        this.name = requestDto.getName();
        this.introduction = requestDto.getIntroduction();
    }

    public void validateAuthority(User user) {
        if (!this.manager.getId().equals(user.getId())) {
            throw new CustomException(ErrorType.BOARD_ACCESS_FORBIDDEN);
        }
    }

}
