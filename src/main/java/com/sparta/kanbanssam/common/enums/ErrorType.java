package com.sparta.kanbanssam.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
    // user
    DUPLICATE_ACCOUNT_ID(HttpStatus.LOCKED, "이미 아이디가 존재합니다."),
    DUPLICATE_EMAIL(HttpStatus.LOCKED, "이미 이메일이 존재합니다."),

    // column
    COLUMN_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 컬럼입니다."),

    // card
    CARD_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 카드입니다."),
    CARD_ACCESS_FORBIDDEN(HttpStatus.FORBIDDEN, "카드 작성자 및 매니저만 접근할 수 있습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
