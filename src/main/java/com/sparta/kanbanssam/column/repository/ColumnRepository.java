package com.sparta.kanbanssam.column.repository;

import com.sparta.kanbanssam.column.entity.Columns;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColumnRepository extends JpaRepository<Columns, Long> {
    Long countAllByBoardId(Long boardId);

    boolean existsByBoardIdAndName(Long id, String name);
}
