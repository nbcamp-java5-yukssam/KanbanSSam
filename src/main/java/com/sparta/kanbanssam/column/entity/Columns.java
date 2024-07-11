package com.sparta.kanbanssam.column.entity;

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

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "columns", orphanRemoval = true)
    private List<Card> cardList;

    @Builder
    public Columns(Long id) {
        this.id = id;
    }
}
