package com.sparta.kanbanssam.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserStatus {
    ENABLED("enable"),
    DISABLED("disable"),
    UNAUTHORIZED("unauthorized");

    private final String status;

}
