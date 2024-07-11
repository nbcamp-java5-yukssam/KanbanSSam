package com.sparta.kanbanssam.card.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CardRequestDto {

    @NotBlank(message = "제목을 입력해주세요.")
    @Size(min = 1, max = 50, message = "제목은 최소 1글자 이상, 최대 50글자 이하여야 합니다.")
    private String title;               // 제목
    @Size(max = 100, message = "내용은 최대 100글자 이하여야 합니다.")
    private String content;             // 내용
    @Size(max = 20, message = "담당자는 최대 20글자 이하여야 합니다.")
    private String responsiblePerson;   // 담당자
    private LocalDateTime deadline;     // 마감일자
}
