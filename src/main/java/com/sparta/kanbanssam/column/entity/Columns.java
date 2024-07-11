package com.sparta.kanbanssam.column.entity;

import com.sparta.kanbanssam.board.entity.Board;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
