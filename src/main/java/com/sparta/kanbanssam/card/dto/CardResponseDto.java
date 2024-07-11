package com.sparta.kanbanssam.card.dto;

import com.sparta.kanbanssam.card.entity.Card;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CardResponseDto {

    private Long cardId;                // 카드 고유번호
    private String title;               // 제목
    private String content;             // 내용
    private String responsiblePerson;   // 담당자
    private LocalDateTime deadline;     // 마감일자

    public CardResponseDto(Card card) {
        this.cardId = card.getId();
        this.title = card.getTitle();
        this.content = card.getContent();
        this.responsiblePerson = card.getResponsiblePerson();
        this.deadline = card.getDeadline();
    }
}
