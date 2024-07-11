package com.sparta.kanbanssam.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    USER("USER"),
    MANAGER("MANAGER");

    private final String roleValue;
}
