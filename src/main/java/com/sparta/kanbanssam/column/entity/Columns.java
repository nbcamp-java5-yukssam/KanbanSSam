package com.sparta.kanbanssam.column.entity;

import com.sparta.kanbanssam.board.entity.Board;
import com.sparta.kanbanssam.card.entity.Card;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Table(name = "Columns")
@NoArgsConstructor
public class Columns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id",nullable = false)
    private Board board;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "columns", orphanRemoval = true)
    private List<Card> cardList;

    @Column(nullable = false)
    private Long orders;

    @Builder
    public Columns(Long id, Board board, String name, Long orders) {
        this.id = id;
        this.board = board;
        this.name=name;
        this.orders = orders;
    }
}
