package com.sparta.kanbanssam.comment.dto;

import com.sparta.kanbanssam.comment.entity.Comment;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CommentCreatedResponseDto {
    private String comment;
    private LocalDateTime createdAt;

    public CommentCreatedResponseDto(Comment comment) {
        this.comment = comment.getComment();
        this.createdAt = comment.getCreatedAt();
    }
}
