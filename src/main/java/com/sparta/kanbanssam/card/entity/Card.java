package com.sparta.kanbanssam.card.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name="Card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;
}
