package com.sparta.kanbanssam.Column.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "Column")
public class Column {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @jakarta.persistence.Column(nullable = false, unique = true)
    private Long id;
}
