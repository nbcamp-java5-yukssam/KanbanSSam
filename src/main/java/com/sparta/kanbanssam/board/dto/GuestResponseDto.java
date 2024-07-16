package com.sparta.kanbanssam.board.dto;

import com.sparta.kanbanssam.board.entity.Guest;
import lombok.Getter;

@Getter
public class GuestResponseDto {

    private String guestName;
    private String guestEmail;

    public GuestResponseDto(Guest guest) {
        this.guestName = guest.getUser().getName();
        this.guestEmail = guest.getUser().getEmail();
    }
}
