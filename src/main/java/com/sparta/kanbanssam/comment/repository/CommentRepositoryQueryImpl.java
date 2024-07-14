package com.sparta.kanbanssam.comment.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.kanbanssam.comment.entity.Comment;
import com.sparta.kanbanssam.comment.entity.QComment;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryQueryImpl implements CommentRepositoryQuery {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Comment> findCommentByCardId(Long cardId) {
        QComment comment = QComment.comment1;

        return queryFactory
            .selectFrom(comment)
            .where(comment.card.id.eq(cardId))
            .orderBy(comment.createdAt.desc())
            .fetch();
    }
}
