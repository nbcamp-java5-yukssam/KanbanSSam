package com.sparta.kanbanssam.security.dto;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    private String accountId;
    private String password;

}
