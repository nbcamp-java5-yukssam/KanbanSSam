package com.sparta.kanbanssam.comment.service;

import com.sparta.kanbanssam.card.entity.Card;
import com.sparta.kanbanssam.card.repository.CardRepository;
import com.sparta.kanbanssam.comment.dto.CommentCreatedResponseDto;
import com.sparta.kanbanssam.comment.dto.CommentGetResponseDto;
import com.sparta.kanbanssam.comment.dto.CommentRequestDto;
import com.sparta.kanbanssam.comment.entity.Comment;
import com.sparta.kanbanssam.comment.repository.CommentRepository;
import com.sparta.kanbanssam.common.enums.ErrorType;
import com.sparta.kanbanssam.common.exception.CustomException;
import com.sparta.kanbanssam.user.entity.User;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CardRepository cardRepository;

    @Transactional
    public CommentCreatedResponseDto createComment(Long cardId, CommentRequestDto requestDto, User user) {
        Card card = cardRepository.findById(cardId).orElseThrow(
            ()-> new CustomException(ErrorType.CARD_NOT_FOUND));

//        Comment comment = Comment.builder()
//            .comment(requestDto.getComment())
//            .user(user)
//            .card(card)
//            .build();

        Comment comment = Comment.builder()
            .comment(requestDto.getComment())
            .user(user)
            .card(card)
            .build();

        commentRepository.save(comment);
        return new CommentCreatedResponseDto(comment);
    }


    public ResponseEntity<?> getComment(Long cardId) {
        List<Comment> commentList = commentRepository.findCommentByCardId(cardId);

        List<CommentGetResponseDto> responseList = commentList.stream()
            .map(CommentGetResponseDto::new)
            .toList();

        return ResponseEntity.ok(responseList);
    }
}
