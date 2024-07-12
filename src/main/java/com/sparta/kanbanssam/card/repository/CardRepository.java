package com.sparta.kanbanssam.card.repository;

import com.sparta.kanbanssam.card.dto.CardRequestDto;
import com.sparta.kanbanssam.card.entity.Card;
import com.sparta.kanbanssam.column.entity.Columns;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    Long countAllByColumnsId(Long columnId);

    List<Card> findAllByColumnsOrderByOrders(Columns columns);
}
