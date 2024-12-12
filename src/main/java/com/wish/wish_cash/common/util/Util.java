package com.wish.wish_cash.common.util;

import com.wish.wish_cash.wish.domain.Frequency;
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
    public static LocalDate calculateEndDate(LocalDate startAt, Long currentAmount, Long dayDeposit, Frequency frequency) {
        long totalDays = currentAmount / dayDeposit;

        switch (frequency) {
            case DAILY -> {
                return startAt.plusDays(totalDays);
            }
            case WEEKLY -> {
                return calculateWeeklyEndDate(startAt, totalDays);
            }
            case MONTHLY -> {
                return calculateMonthlyEndDate(startAt, totalDays);
            }
            default -> throw new IllegalArgumentException("지원하지 않습니다.");
        }
    }

    private static LocalDate calculateWeeklyEndDate(LocalDate starAt, long totalDays) {
        long weeks = totalDays;
        long remainDays = totalDays * 7;
        return starAt.plusDays(remainDays);
    }

    private static LocalDate calculateMonthlyEndDate(LocalDate startAt, long totalDays) {
        long months = totalDays;
        long remainDays = totalDays % 30;

        return startAt.plusMonths(months);
    }

    // 수정 시 마김일 계산 로직
    public static LocalDate updateCalculateEndDate(LocalDate startAt, Long currentAmount, Long dayDeposit) {
        long calculateDay = calculatePrice(startAt, currentAmount, dayDeposit) / dayDeposit;
        LocalDate expirationDate = LocalDate.now().plusDays(calculateDay);
        return expirationDate;
    }

    // dayDeposit 수정 시 남은 금액 로직
    private static long calculatePrice(LocalDate startAt, Long currentAmount, Long dayDeposit) {
        long days = ChronoUnit.DAYS.between(startAt, LocalDate.now());
        long updatePrice =  currentAmount - (dayDeposit * days);
        return updatePrice;
    }

}
