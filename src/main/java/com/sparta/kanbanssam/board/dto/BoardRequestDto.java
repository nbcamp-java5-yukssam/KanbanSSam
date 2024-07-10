package com.sparta.kanbanssam.board.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class BoardRequestDto {

    @NotNull(message = "보드의 이름을 입력해주세요.")
    @Size(min = 1, max = 20, message = "최대 20글자 까지 입력이 가능합니다.")
    private String name; //보드제목

    @Size(min = 1, max = 50, message = "무엇에 관한 보드인지 설명을 입력해주세요")
    private String introduction; //보드내용


}
