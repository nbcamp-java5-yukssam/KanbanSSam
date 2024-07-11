package com.sparta.kanbanssam.card.service;

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

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final ColumnRepository columnRepository;

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
        Card card = getCard(cardId);

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
        Card card = getCard(cardId);
        card.validateAuthority(user);
        cardRepository.delete(card);
    }

    /**
     * Id로 Card 엔티티 찾기
     * @param cardId 카드 ID
     * @return 카드 정보
     */
    private Card getCard(Long cardId) {
        return cardRepository.findById(cardId)
                .orElseThrow(()-> new CustomException(ErrorType.CARD_NOT_FOUND));
    }
}
