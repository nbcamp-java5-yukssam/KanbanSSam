package com.sparta.kanbanssam.user.dto;

import lombok.Getter;

@Getter
public class UserResponseDto {

    private String name;
    private String email;

    public UserResponseDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

}
