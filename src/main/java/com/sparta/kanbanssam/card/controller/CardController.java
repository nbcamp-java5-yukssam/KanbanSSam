package com.sparta.kanbanssam.card.controller;

import com.sparta.kanbanssam.card.dto.CardRequestDto;
import com.sparta.kanbanssam.card.dto.CardResponseDto;
import com.sparta.kanbanssam.card.service.CardService;
import com.sparta.kanbanssam.user.entity.User;
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
//    @ResponseBody
//    @PostMapping("/{columnId}/cards")
//    public ResponseEntity<?> createCard(@PathVariable Long columnId, @RequestBody CardRequestDto requestDto) {
//        // todo : secutity 구현 완료 시 userDetails 로 수정
//        User user = User.builder().id(1L).build();
//        CardResponseDto responseDto = cardService.createCard(columnId, requestDto, user);
//        return ResponseEntity.ok(responseDto);
//    }

    @GetMapping("/card/view")
    public String cardView() {
        return "/card/card";
    }
}
