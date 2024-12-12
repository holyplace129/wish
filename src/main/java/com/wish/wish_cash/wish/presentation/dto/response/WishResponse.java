package com.wish.wish_cash.wish.presentation.dto.response;

import com.wish.wish_cash.common.util.Util;
import com.wish.wish_cash.wish.domain.Wish;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@RequiredArgsConstructor
public class WishResponse {

    private final Integer id;
    private final String title;
    private final String content;
    private final String image;
    private final LocalDate startAt;
    private final long daysBetween;

    public static WishResponse from(Wish wish) {
        long daysBetween = ChronoUnit.DAYS.between(LocalDate.now(), wish.getExpirationAt());
        return new WishResponse(
                wish.getId(),
                wish.getTitle(),
                wish.getContent(),
                wish.getImage(),
                wish.getStartAt(),
                daysBetween
        );
    }
}
