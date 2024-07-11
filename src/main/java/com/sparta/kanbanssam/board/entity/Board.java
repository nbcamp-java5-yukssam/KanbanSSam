package com.sparta.kanbanssam.board.entity;

import com.sparta.kanbanssam.user.entity.User;
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

    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private User manager;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String introduction;

    @Builder
    public Board(Long id, User manager, String name, String introduction) {
        this.id = id;
        this.manager = manager;
        this.name = name;
        this.introduction = introduction;
    }


}
