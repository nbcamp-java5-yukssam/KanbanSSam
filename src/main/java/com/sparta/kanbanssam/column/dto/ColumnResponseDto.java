package com.sparta.kanbanssam.column.dto;

import com.sparta.kanbanssam.column.entity.Columns;
import com.sparta.kanbanssam.column.service.ColumnService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ColumnResponseDto {
    private Long columnId;                // 컬럼 고유번호
    private String name;                  // 이름

    public ColumnResponseDto(Columns column){
        this.columnId=column.getId();
        this.name=column.getName();
    }


}
