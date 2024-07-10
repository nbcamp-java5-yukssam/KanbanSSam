package com.sparta.kanbanssam.board.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table (name = "Board")
public class Board {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String introduction;

    @Builder
    public Board(Long id, String name, String introduction) {
        this.id = id;
        this.name = name;
        this.introduction = introduction;
    }


}
