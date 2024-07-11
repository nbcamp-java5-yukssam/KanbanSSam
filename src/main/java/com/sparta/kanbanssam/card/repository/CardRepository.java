package com.sparta.kanbanssam.card.repository;

import com.sparta.kanbanssam.card.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
