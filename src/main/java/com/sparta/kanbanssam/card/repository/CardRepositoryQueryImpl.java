package com.sparta.kanbanssam.card.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.kanbanssam.board.entity.Board;
import com.sparta.kanbanssam.card.dto.CardListByColumnsResponseDto;
import com.sparta.kanbanssam.card.dto.CardResponseDto;
import com.sparta.kanbanssam.column.entity.Columns;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.sparta.kanbanssam.board.entity.QBoard.board;
import static com.sparta.kanbanssam.card.entity.QCard.card;
import static com.sparta.kanbanssam.column.entity.QColumns.columns;

@RequiredArgsConstructor
public class CardRepositoryQueryImpl implements CardRepositoryQuery {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CardListByColumnsResponseDto> getCardListByColumnAtBoard(Board board) {
        List<CardListByColumnsResponseDto> responseDtoList = new ArrayList<>();

        List<Columns> columnsList = jpaQueryFactory
                .select(columns)
                .from(columns)
                .where(boardIdEq(board))
                .orderBy(columns.orders.asc())
                .fetch();

        for (Columns column : columnsList) {
            CardListByColumnsResponseDto responseDto = CardListByColumnsResponseDto.builder()
                    .columnId(column.getId())
                    .columnName(column.getName())
                    .columnOrder(column.getOrders())
                    .cardList(getCardListAtColumn(column.getId()))
                    .build();
            responseDtoList.add(responseDto);
        }
        return responseDtoList;
    }

    @Override
    public List<CardResponseDto> getCardListAtColumn(Long columnId) {
        return jpaQueryFactory
                .select(card)
                .from(card)
                .where(columnIdEq(columnId))
                .orderBy(card.orders.asc())
                .fetch()
                .stream()
                .map(CardResponseDto::new)
                .toList();
    }

    @Override
    public List<CardResponseDto> getCardListByBoard(Long boardId) {
        return jpaQueryFactory
                .select(card)
                .from(card)
                .where(
                        card.columns.in(
                                JPAExpressions
                                        .select(columns)
                                        .from(columns)
                                        .where(boardIdEq(boardId))
                        )
                )
                .orderBy(card.createdAt.desc())
                .fetch()
                .stream()
                .map(CardResponseDto::new)
                .toList();
    }

    private BooleanExpression boardIdEq(Board boardEq) {
        return Objects.nonNull(boardEq) ? board.eq(boardEq) : null;
    }

    private BooleanExpression columnIdEq(Long columnId) {
        return Objects.nonNull(columnId) ? columns.id.eq(columnId) : null;
    }

    private BooleanExpression boardIdEq(Long boardId) {
        return Objects.nonNull(boardId) ? board.id.eq(boardId) : null;
    }
}
