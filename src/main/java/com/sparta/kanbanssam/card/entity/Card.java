package com.sparta.kanbanssam.card.entity;

import com.sparta.kanbanssam.card.dto.CardRequestDto;
import com.sparta.kanbanssam.column.entity.Columns;
import com.sparta.kanbanssam.common.entity.Timestamped;
import com.sparta.kanbanssam.common.enums.ErrorType;
import com.sparta.kanbanssam.common.exception.CustomException;
import com.sparta.kanbanssam.user.entity.User;
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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "column_id", nullable = false)
    private Columns columns;

    @Column(nullable = false)
    private String title;

    @Column
    private String content;

    @Column
    private String responsiblePerson;

    @Column
    private LocalDateTime deadline;

    @Column(nullable = false)
    private Long orders;

    @Builder
    public Card(Long id, User user, Columns columns, String title, String content, String responsiblePerson, LocalDateTime deadline, Long orders) {
        this.id = id;
        this.user = user;
        this.columns = columns;
        this.title = title;
        this.content = content;
        this.responsiblePerson = responsiblePerson;
        this.deadline = deadline;
        this.orders = orders;
    }

    /**
     * 사용자 카드 접근 권한 검증
     * <p>
     *     작성자 또는 매니저가 아닌 경우 예외처리
     * </p>
     * @param user 회원 정보
     */
    public void validateAuthority(User user) {
        // todo : User 구현 완료 시 UserRole 권한 체크 로직도 추가
        if (!this.getUser().getId().equals(user.getId())) {
            throw new CustomException(ErrorType.CARD_ACCESS_FORBIDDEN);
        }
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
