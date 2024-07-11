package com.sparta.kanbanssam.comment.repository;

import com.sparta.kanbanssam.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
