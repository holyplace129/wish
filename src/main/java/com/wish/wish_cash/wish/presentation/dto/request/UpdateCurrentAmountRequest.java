package com.wish.wish_cash.wish.presentation.dto.request;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UpdateCurrentAmountRequest {

    private final Long currentAmount;
    private final LocalDate expirationAt;

    public UpdateCurrentAmountRequest(Long currentAmount, LocalDate expirationAt) {
        this.currentAmount = currentAmount;
        this.expirationAt = expirationAt;
    }
}
