package com.wish.wish_cash.wish.presentation.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class WishRequest {

    private final String title;
    private final String content;
    private final String image;
    private final Long price;
    private final Long dayDeposit;

    @Builder
    public WishRequest(String title, String content, String image, Long price, Long dayDeposit) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.price = price;
        this.dayDeposit = dayDeposit;
    }
}
