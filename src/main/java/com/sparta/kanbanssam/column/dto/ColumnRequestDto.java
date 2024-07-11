package com.sparta.kanbanssam.column.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ColumnRequestDto {
    @NotBlank(message = "컬럼의 이름을 입력해주세요.")
    @Size(min = 1, max = 50, message = "컬럼이름은 최소 1글자 이상, 최대 50글자 이하여야 합니다.")
    private String name;               // 컬럼이름

}
