package com.wish.wish_cash.wish.presentation.dto.request;

import com.wish.wish_cash.wish.domain.Frequency;
import com.wish.wish_cash.wish.domain.Wish;
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
    private final LocalDate startAt;
    private final Frequency frequency;
    private final LocalDate expirationAt;

    @Builder(toBuilder = true)
    public WishRequest(String title, String content, String image, Long price, Long dayDeposit, LocalDate startAt, Frequency frequency, LocalDate expirationAt) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.price = price;
        this.dayDeposit = dayDeposit;
        this.startAt = startAt;
        this.frequency = frequency;
        this.expirationAt = expirationAt;
    }


}
