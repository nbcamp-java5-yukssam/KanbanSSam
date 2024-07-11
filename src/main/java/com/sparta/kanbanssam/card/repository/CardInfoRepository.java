package com.sparta.kanbanssam.card.repository;

import com.sparta.kanbanssam.card.entity.CardInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardInfoRepository extends JpaRepository<CardInfo, Long> {
    Long countAllByColumnsId(Long columnId);
}
