package com.sparta.kanbanssam.card.entity;

import com.sparta.kanbanssam.column.entity.Columns;
import com.sparta.kanbanssam.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "CardInfo")
@NoArgsConstructor
public class CardInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "column_id")
    private Columns columns;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @Builder
    public CardInfo(Long id, User user, Columns columns, Card card) {
        this.id = id;
        this.user = user;
        this.columns = columns;
        this.card = card;
    }
}
