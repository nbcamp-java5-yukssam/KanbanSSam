package com.sparta.kanbanssam.card.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CardListByColumnsResponseDto {

    private Long columnId;
    private String columnName;
    private Long columnOrder;
    private List<CardResponseDto> cardList;

    @Builder
    public CardListByColumnsResponseDto(Long columnId, String columnName, Long columnOrder, List<CardResponseDto> cardList) {
        this.columnId = columnId;
        this.columnName = columnName;
        this.columnOrder = columnOrder;
        this.cardList = cardList;
    }
}
