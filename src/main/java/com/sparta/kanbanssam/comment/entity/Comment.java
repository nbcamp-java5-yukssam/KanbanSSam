package com.sparta.kanbanssam.comment.entity;

import com.sparta.kanbanssam.card.entity.Card;
import com.sparta.kanbanssam.common.entity.Timestamped;
import com.sparta.kanbanssam.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "comment")
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Comment(Long id, String comment, Card card, User user) {
        this.id = id;
        this.comment = comment;
        this.card = card;
        this.user = user;
    }
}
