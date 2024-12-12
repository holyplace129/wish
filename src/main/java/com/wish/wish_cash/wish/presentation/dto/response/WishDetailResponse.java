package com.wish.wish_cash.wish.presentation.dto.response;

import com.wish.wish_cash.common.util.Util;
import com.wish.wish_cash.wish.domain.Frequency;
import com.wish.wish_cash.wish.domain.Wish;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@RequiredArgsConstructor
public class WishDetailResponse {

    private final Integer id;
    private final String title;
    private final String content;
    private final String image;
    private final Frequency frequency;
    private final Long price;
    private final Long currentAmount;
    private final Long dayDeposit;
    private final LocalDate startAt;
    private final LocalDate expirationAt;
    private final long daysBetween;

    public static WishDetailResponse of(Wish wish) {
        long daysBetween = ChronoUnit.DAYS.between(LocalDate.now(), wish.getExpirationAt());
        return new WishDetailResponse(
                wish.getId(),
                wish.getTitle(),
                wish.getContent(),
                wish.getImage(),
                wish.getFrequency(),
                wish.getPrice(),
                wish.getCurrentAmount(),
                wish.getDayDeposit(),
                wish.getStartAt(),
                wish.getExpirationAt(),
                daysBetween
        );
    }
}
