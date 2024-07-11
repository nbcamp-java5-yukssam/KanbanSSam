package com.sparta.kanbanssam.board.repository;

import com.sparta.kanbanssam.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
