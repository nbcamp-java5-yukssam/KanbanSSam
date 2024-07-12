package com.sparta.kanbanssam.comment.dto;

import com.sparta.kanbanssam.comment.entity.Comment;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CommentGetResponseDto {
    private String userName;
    private String comment;
    private LocalDateTime createdAt;

    public CommentGetResponseDto(Comment comment) {
        this.userName = comment.getUser().getName();
        this.comment = comment.getComment();
        this.createdAt = comment.getCreatedAt();
    }
}
