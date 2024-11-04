package com.wish.wish_cash.wish.presentation.dto;

import com.wish.wish_cash.util.Util;
import com.wish.wish_cash.wish.domain.Wish;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class WishResponse {

    private final Integer id;
    private final String title;
    private final String content;
    private final String image;
    private final int day;

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
