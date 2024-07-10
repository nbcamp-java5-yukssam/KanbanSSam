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

    //board
    USER_NOT_FOUND(HttpStatus.LOCKED, "유저가 존재하지 않습니다.."),
    USER_NOT_AUTHORIZATION(HttpStatus.LOCKED, "보드를 생성할 권한이 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
