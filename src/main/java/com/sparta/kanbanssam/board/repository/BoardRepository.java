package com.sparta.kanbanssam.board.repository;

import com.sparta.kanbanssam.board.entity.Board;
import com.sparta.kanbanssam.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>  {
    List<Board> findAllByUserId(Long userId);
}
