package com.sparta.kanbanssam.card.entity;

import com.sparta.kanbanssam.card.dto.CardRequestDto;
import com.sparta.kanbanssam.column.entity.Columns;
import com.sparta.kanbanssam.common.entity.Timestamped;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="Card")
@NoArgsConstructor
public class Card extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(nullable = false, unique = true)
    private Long id;                                        // 고유 번호

    @ManyToOne
    @JoinColumn(name = "column_id", nullable = false)
    private Columns column;                                 // 컬럼

    @Column(nullable = false)
    private String title;                                   // 제목

    @Column
    private String content;                                 // 내용

    @Column
    private String responsiblePerson;                       // 담당자

    @Column
    private LocalDateTime deadline;                         // 마감일자

    @Column(nullable = false)
    private Long orders;                                      // 순서

    @Builder
    public Card(Long id, Columns column, String title, String content, String responsiblePerson, LocalDateTime deadline, Long orders) {
        this.id = id;
        this.column = column;
        this.title = title;
        this.content = content;
        this.responsiblePerson = responsiblePerson;
        this.deadline = deadline;
        this.orders = orders;
    }

    /**
     * 카드 수정
     * @param requestDto 카드 수정 정보
     */
    public void update(CardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.responsiblePerson = requestDto.getResponsiblePerson();
        this.deadline = requestDto.getDeadline();
    }

    /**
     * 카드 수정
     * @param title 제목
     * @param content 내용
     * @param responsiblePerson 담당자
     * @param deadline 마감일자
     */
    public void update(String title, String content, String responsiblePerson, LocalDateTime deadline) {
        this.title = title;
        this.content = content;
        this.responsiblePerson = responsiblePerson;
        this.deadline = deadline;
    }
}
