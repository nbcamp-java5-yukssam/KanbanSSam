package com.sparta.kanbanssam.comment.controller;


import com.sparta.kanbanssam.comment.dto.CommentCreatedResponseDto;
import com.sparta.kanbanssam.comment.dto.CommentRequestDto;
import com.sparta.kanbanssam.comment.service.CommentService;
import com.sparta.kanbanssam.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/cards/{cardId}/comments")
    public ResponseEntity<?> addComment(@PathVariable("cardId") Long cardId, @RequestBody CommentRequestDto requestDto) {
        // todo : secutity 구현 완료 시 userDetails 로 수정
        User user = User.builder().build();
        CommentCreatedResponseDto responseDto = commentService.createComment(cardId, requestDto, user);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/comments/view")
    public String cardView() {return "/comment/comment";}
}
