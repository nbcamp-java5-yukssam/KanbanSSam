package com.sparta.kanbanssam.card.controller;

import com.sparta.kanbanssam.card.dto.CardRequestDto;
import com.sparta.kanbanssam.card.dto.CardResponseDto;
import com.sparta.kanbanssam.card.service.CardService;
import com.sparta.kanbanssam.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/column")
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
    @PostMapping("/{columnId}/cards")
    public ResponseEntity<?> createCard(
            @PathVariable Long columnId,
            @Valid @RequestBody CardRequestDto requestDto) {
        // todo : secutity 구현 완료 시 userDetails 로 수정
        User user = User.builder().id(1L).build();
        CardResponseDto responseDto = cardService.createCard(columnId, requestDto, user);
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
    @PutMapping("/{columnId}/cards/{cardId}")
    public ResponseEntity<?> updateCard(
            @PathVariable Long columnId,
            @PathVariable Long cardId,
            @Valid @RequestBody CardRequestDto requestDto) {
        // todo : secutity 구현 완료 시 userDetails 로 수정
        User user = User.builder().id(1L).build();
        CardResponseDto responseDto = cardService.updateCard(cardId, requestDto, user);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * test용 view
     * @return card.html
     */
    @GetMapping("/card/view")
    public String cardView() {
        return "/card/card";
    }
}
