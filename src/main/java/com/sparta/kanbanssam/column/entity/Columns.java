package com.sparta.kanbanssam.column.entity;

import com.sparta.kanbanssam.board.entity.Board;
import com.sparta.kanbanssam.card.dto.CardRequestDto;
import com.sparta.kanbanssam.card.entity.Card;
import com.sparta.kanbanssam.column.dto.ColumnRequestDto;
import com.sparta.kanbanssam.common.enums.ErrorType;
import com.sparta.kanbanssam.common.exception.CustomException;
import com.sparta.kanbanssam.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Table(name = "Columns")
@NoArgsConstructor
public class Columns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long orders;

    @OneToMany(mappedBy = "columns", orphanRemoval = true)
    private List<Card> cardList;

    @Builder
    public Columns(Long id, Board board, String name, Long orders) {
        this.id = id;
        this.board = board;
        this.name=name;
        this.orders = orders;
    }

    /**
     * 사용자 컬럼 접근 권한 검증
     * <p>
     *     작성자가 아닌경우 예외처리
     * </p>
     * @param user 회원 정보
     */
    public void validateAuthority(User user) {
        if (!this.getBoard().getManager().getId().equals(user.getId())) {
            throw new CustomException(ErrorType.COLUMN_ACCESS_FORBIDDEN);
        }
    }

    /**
     * 카드 수정
     * @param requestDto 카드 수정 정보
     */
    public void update(ColumnRequestDto requestDto) {
        this.name = requestDto.getName();
    }
}
