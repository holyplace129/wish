package com.wish.wish_cash.wish.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wish.wish_cash.wish.domain.Frequency;
import com.wish.wish_cash.wish.domain.QWish;
import com.wish.wish_cash.wish.domain.Wish;
import com.wish.wish_cash.wish.presentation.dto.WishResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WishQuerydslRepository {

    private final JPAQueryFactory queryFactory;

    public List<Wish> findWishByFrequency(Frequency frequency) {
        QWish wish = QWish.wish;

        BooleanBuilder builder = new BooleanBuilder();
        if (frequency != null) {
            builder.and(wish.frequency.eq(frequency));
        }

        return queryFactory
                .select(wish)
                .where(builder)
                .fetch();
    }
}
