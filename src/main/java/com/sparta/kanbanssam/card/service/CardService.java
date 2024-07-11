package com.sparta.kanbanssam.card.service;

import com.sparta.kanbanssam.card.dto.CardRequestDto;
import com.sparta.kanbanssam.card.dto.CardResponseDto;
import com.sparta.kanbanssam.card.entity.Card;
import com.sparta.kanbanssam.card.entity.CardInfo;
import com.sparta.kanbanssam.card.repository.CardInfoRepository;
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
    private final CardInfoRepository cardInfoRepository;

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

        // 해당 컬럼에 존재하는 카드 총 개수
        Long cardCnt = cardInfoRepository.countAllByColumnsId(columnId);

        Card card = Card.builder()
                .column(columns)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .responsiblePerson(requestDto.getResponsiblePerson())
                .deadline(requestDto.getDeadline())
                .orders(cardCnt + 1)
                .build();

        Card saveCard = cardRepository.save(card);

        CardInfo cardInfo = CardInfo.builder()
                .user(user)
                .columns(columns)
                .card(saveCard)
                .build();

        cardInfoRepository.save(cardInfo);
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
        CardInfo cardInfo = cardInfoRepository.findByCardId(cardId)
                .orElseThrow(()-> new CustomException(ErrorType.CARD_NOT_FOUND));

        // 카드 작성자가 아닐 경우 예외처리
        // todo : User 구현 완료 시 UserRole 권한 체크 로직도 추가
        if (!cardInfo.getUser().getId().equals(user.getId())) {
            throw new CustomException(ErrorType.CARD_ACCESS_FORBIDDEN);
        }

        Card card = cardInfo.getCard();
        card.update(requestDto);

        Card saveCard = cardRepository.save(card);

        return new CardResponseDto(saveCard);
    }
}
