package com.wish.wish_cash.wish.presentation.dto;

import com.wish.wish_cash.util.Util;
import com.wish.wish_cash.wish.domain.Wish;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class WishDetailResponse {

    private final Integer id;
    private final String title;
    private final String content;
    private final String image;
    private final Long price;
    private final Long dayDeposit;
    private final LocalDate startAt;
    private final LocalDate expirationAt;
    private final long daysBetween;

    public static WishDetailResponse of(Wish wish) {
        return new WishDetailResponse(
                wish.getId(),
                wish.getTitle(),
                wish.getContent(),
                wish.getImage(),
                wish.getPrice(),
                wish.getDayDeposit(),
                wish.getStartAt(),
                wish.getExpirationAt(),
                Util.calculateDate(wish.getExpirationAt())
        );
    }
}
