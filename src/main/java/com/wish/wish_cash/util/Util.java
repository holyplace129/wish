package com.wish.wish_cash.util;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
public class Util {

    public static long calculateDate(LocalDate expirationAt) {
        long daysBetween = ChronoUnit.DAYS.between(LocalDate.now(), expirationAt);
        return daysBetween;
    }

    public static LocalDate calculateEndDate(Long price, Long dayDeposit) {
        // price / dayDeposit = int
        // now + int = expirationAt
        Long calculateDay = price / dayDeposit;
        LocalDate expirationDate = LocalDate.now().plusDays(calculateDay);
        return expirationDate;
    }
}
