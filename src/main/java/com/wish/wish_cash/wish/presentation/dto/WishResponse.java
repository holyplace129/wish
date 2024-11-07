package com.wish.wish_cash.wish.presentation.dto;

import com.wish.wish_cash.util.Util;
import com.wish.wish_cash.wish.domain.Wish;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class WishResponse {

    private final Integer id;
    private final String title;
    private final String content;
    private final String image;
    private final long daysBetween;

    public static WishResponse from(Wish wish) {
        return new WishResponse(
                wish.getId(),
                wish.getTitle(),
                wish.getContent(),
                wish.getImage(),
                Util.calculateDate(wish.getExpirationAt())
        );
    }
}
