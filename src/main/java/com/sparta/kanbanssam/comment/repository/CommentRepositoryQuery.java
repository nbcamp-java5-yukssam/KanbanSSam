package com.sparta.kanbanssam.comment.repository;

import com.sparta.kanbanssam.comment.entity.Comment;
import java.util.List;

public interface CommentRepositoryQuery {
    List<Comment> findCommentByCardId(Long cardId);
}
