package com.wish.wish_cash.util;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
public class Util {

    // D-day 계산 로직
    public static long calculateDate(LocalDate expirationAt) {
        long daysBetween = ChronoUnit.DAYS.between(LocalDate.now(), expirationAt);
        return daysBetween;
    }

    // 마감일 계산 로직
    public static LocalDate calculateEndDate(LocalDate startAt, Long price, Long dayDeposit) {
        // price / dayDeposit = int
        // now + int = expirationAt
        long plusDays = price / dayDeposit;
        LocalDate expirationDate = startAt.plusDays(plusDays);
        return expirationDate;
    }

    // 수정 시 마김일 계산 로직
    public static LocalDate updateCalculateEndDate(LocalDate startAt, Long price, Long dayDeposit) {
        long calculateDay = calculatePrice(startAt, price, dayDeposit) / dayDeposit;
        LocalDate expirationDate = LocalDate.now().plusDays(calculateDay);
        return expirationDate;
    }

    // dayDeposit 수정 시 남은 금액 로직
    public static long calculatePrice(LocalDate startAt, Long price, Long dayDeposit) {
        long days = ChronoUnit.DAYS.between(startAt, LocalDate.now());
        long updatePrice =  price - (dayDeposit * days);
        return updatePrice;
    }
}
