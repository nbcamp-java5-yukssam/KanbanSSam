package com.sparta.kanbanssam.board.repository;

import com.sparta.kanbanssam.board.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    boolean existsByUserId(Long inviteUserId);
}
