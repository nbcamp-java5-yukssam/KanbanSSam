package com.sparta.kanbanssam.card.service;

import com.sparta.kanbanssam.board.entity.Board;
import com.sparta.kanbanssam.board.repository.BoardRepository;
import com.sparta.kanbanssam.card.dto.CardListByColumnsResponseDto;
import com.sparta.kanbanssam.card.dto.CardRequestDto;
import com.sparta.kanbanssam.card.dto.CardResponseDto;
import com.sparta.kanbanssam.card.entity.Card;
import com.sparta.kanbanssam.card.repository.CardRepository;
import com.sparta.kanbanssam.column.entity.Columns;
import com.sparta.kanbanssam.column.repository.ColumnRepository;
import com.sparta.kanbanssam.common.enums.ErrorType;
import com.sparta.kanbanssam.common.exception.CustomException;
import com.sparta.kanbanssam.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final ColumnRepository columnRepository;
    private final BoardRepository boardRepository;

    /**
     * 카드 생성
     * @param columnId 컬럼 ID
     * @param requestDto 카드 생성 정보
     * @param user 회원 정보
     * @return 카드 정보
     */
    @Transactional
    public CardResponseDto createCard(Long columnId, CardRequestDto requestDto, User user) {
        // todo : ColumnService에 Column 엔티티 조회 메서드 생성 시 수정
        Columns columns = columnRepository.findById(columnId).orElseThrow(
                ()-> new CustomException(ErrorType.COLUMN_NOT_FOUND));

        Long cardCnt = cardRepository.countAllByColumnsId(columnId);

        Card card = Card.builder()
                .user(user)
                .board(columns.getBoard())
                .columns(columns)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .responsiblePerson(requestDto.getResponsiblePerson())
                .deadline(requestDto.getDeadline())
                .orders(cardCnt + 1)
                .build();

        Card saveCard = cardRepository.save(card);

        return new CardResponseDto(saveCard);
    }

    /**
     * 카드 수정
     * @param cardId 카드 ID
     * @param requestDto 카드 수정 정보
     * @param user 회원 정보
     * @return 카드 정보
     */
    @Transactional
    public CardResponseDto updateCard(Long cardId, CardRequestDto requestDto, User user) {
        Card card = findCard(cardId);

        // 카드 작성자가 아닐 경우 예외처리
        // todo : User 구현 완료 시 UserRole 권한 체크 로직도 추가
        card.validateAuthority(user);
        card.update(requestDto);

        return new CardResponseDto(card);
    }

    /**
     * 카드 삭제
     * @param cardId 카드 ID
     * @param user 회원 정보
     */
    public void deleteCard(Long cardId, User user) {
        Card card = findCard(cardId);
        card.validateAuthority(user);
        cardRepository.delete(card);
    }

    /**
     * 카드 단일 조회
     * @param cardId 카드 ID
     * @return 카드 정보
     */
    public CardResponseDto getCard(Long cardId) {
        return new CardResponseDto(findCard(cardId));
    }

    /**
     * 보드 별 카드 전체 목록 조회
     * @param boardId 보드 ID
     * @return 카드 목록
     */
    public List<CardResponseDto> getCardListByBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()-> new CustomException(ErrorType.BOARD_NOT_FOUND));

        return board.getCardList().stream()
                .map(CardResponseDto::new)
                .toList();
    }

    /**
     * 단일 컬럼 별 카드 목록 조회
     * @param columnId 컬럼 ID
     * @return 카드 목록
     */
    public List<CardResponseDto> getCardList(Long columnId) {
        // todo : ColumnService에 Column 엔티티 조회 메서드 생성 시 수정
        Columns columns = columnRepository.findById(columnId).orElseThrow(
                ()-> new CustomException(ErrorType.COLUMN_NOT_FOUND));

        List<CardResponseDto> cardList = cardRepository.findAllByColumnsOrderByOrders(columns)
                .stream().map(CardResponseDto::new).toList();
        return cardList;
    }

    /**
     * 전체 컬럼 별 카드 목록 조회
     * @param boardId 보드 ID
     * @return 카드 목록
     */
    public List<CardListByColumnsResponseDto> getCardListByColumn(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()-> new CustomException(ErrorType.BOARD_NOT_FOUND));

        List<Columns> columnsList = board.getColumnsList();

        List<CardListByColumnsResponseDto> response = new ArrayList<>();

        for (Columns columns : columnsList) {
            CardListByColumnsResponseDto responseDto = CardListByColumnsResponseDto.builder()
                    .columnId(columns.getId())
                    .columnName(columns.getName())
                    .columnOrder(columns.getOrders())
                    .cardList(
                            columns.getCardList().stream()
                                    .map(CardResponseDto::new)
                                    .toList()
                    )
                    .build();
            response.add(responseDto);
        }

        return response;
    }

    /**
     * Id로 Card 엔티티 찾기
     * @param cardId 카드 ID
     * @return 카드 정보
     */
    public Card findCard(Long cardId) {
        return cardRepository.findById(cardId)
                .orElseThrow(()-> new CustomException(ErrorType.CARD_NOT_FOUND));
    }
}
