package com.sparta.kanbanssam.card.controller;

import com.sparta.kanbanssam.card.dto.CardListByColumnsResponseDto;
import com.sparta.kanbanssam.card.dto.CardRequestDto;
import com.sparta.kanbanssam.card.dto.CardResponseDto;
import com.sparta.kanbanssam.card.service.CardService;
import com.sparta.kanbanssam.security.UserDetailsImpl;
import com.sparta.kanbanssam.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    /**
     * 카드 생성
     * @param columnId 컬럼 ID
     * @param requestDto 카드 생성 정보
     * @return 카드 정보
     */
    @ResponseBody
    @PostMapping("/columns/{columnId}/cards")
    public ResponseEntity<?> createCard(
            @PathVariable Long columnId,
            @Valid @RequestBody CardRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CardResponseDto responseDto = cardService.createCard(columnId, requestDto, userDetails.getUser());
        return ResponseEntity.ok(responseDto);
    }

    /**
     * 카드 수정
     * @param columnId 컬럼 ID
     * @param cardId 카드 ID
     * @param requestDto 카드 수정 정보
     * @return 카드 정보
     */
    @ResponseBody
    @PutMapping("/columns/{columnId}/cards/{cardId}")
    public ResponseEntity<?> updateCard(
            @PathVariable Long columnId,
            @PathVariable Long cardId,
            @Valid @RequestBody CardRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CardResponseDto responseDto = cardService.updateCard(cardId, requestDto, userDetails.getUser());
        return ResponseEntity.ok(responseDto);
    }

    /**
     * 카드 삭제
     * @param columnId 컬럼 ID
     * @param cardId 카드 ID
     */
    @ResponseBody
    @DeleteMapping("/columns/{columnId}/cards/{cardId}")
    public void deleteCard(
            @PathVariable Long columnId,
            @PathVariable Long cardId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        cardService.deleteCard(cardId, userDetails.getUser());
    }

    /**
     * 카드 단일 조회
     * @param cardId 카드 ID
     * @return 카드 정보
     */
    @ResponseBody
    @GetMapping("/cards/{cardId}")
    public ResponseEntity<?> getCard(
            @PathVariable Long cardId) {
        CardResponseDto responseDto = cardService.getCard(cardId);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * 보드 별 카드 전체 목록 조회
     * @param boardId 보드 ID
     * @return 카드 목록
     */
    @ResponseBody
    @GetMapping("/board/{boardId}/cards")
    public ResponseEntity<?> getCardListByBoard(
            @PathVariable Long boardId) {
        List<CardResponseDto> responseDtos = cardService.getCardListByBoard(boardId);
        return ResponseEntity.ok(responseDtos);
    }

    /**
     * 단일 컬럼 카드 목록 조회
     * @param columnId 컬럼 ID
     * @return 카드 목록
     */
    @ResponseBody
    @GetMapping("/columns/{columnId}/cards")
    public ResponseEntity<?> getCardByColumn(
            @PathVariable Long columnId) {
        List<CardResponseDto> cardList = cardService.getCardList(columnId);
        return ResponseEntity.ok(cardList);
    }

    /**
     * 전체 컬럼 별 카드 목록 조회
     * @param boardId 보드 ID
     * @return 컬럼 별 카드 목록
     */
    @ResponseBody
    @GetMapping("/board/{boardId}/cards/byColumns")
    public ResponseEntity<?> getCardListByColumn(
            @PathVariable Long boardId) {
        List<CardListByColumnsResponseDto> responseDto = cardService.getCardListByColumn(boardId);
        return ResponseEntity.ok(responseDto);
    }

//    @ResponseBody
//    @GetMapping("/board/{boardId}/cards/byUser")
//    public ResponseEntity<?> getCardListByUser(@PathVariable Long boardId) {
//
//    }

    /**
     * --------------------------------------------------------------
     */

    /**
     * test용 view
     * @return card.html
     */
    @GetMapping("/columns/cards/view")
    public String cardView() {
        return "/card/card";
    }
}
