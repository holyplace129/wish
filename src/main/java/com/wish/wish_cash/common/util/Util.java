package com.wish.wish_cash.common.util;

import com.wish.wish_cash.wish.domain.Frequency;
import lombok.RequiredArgsConstructor;

import java.time.DayOfWeek;
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
    public static LocalDate calculateEndDate(LocalDate startAt, Long price, Long dayDeposit, Frequency frequency, String dayOfWeek, Integer dayOfMonth) {
        long totalDays = price / dayDeposit;
        // price / dayDeposit = int
        // now + int = expirationAt
//        long plusDays = price / dayDeposit;
//        LocalDate expirationDate = startAt.plusDays(plusDays);
//        return expirationDate;
        switch (frequency) {
            case DAILY -> {
                return startAt.plusDays(totalDays);
            }
            case WEEKLY -> {
                if (dayOfWeek == null) {
                    throw new IllegalArgumentException("요일을 입력해주세요");
                }
                return calculateWeeklyEndDate(startAt, totalDays, DayOfWeek.valueOf(dayOfWeek.toUpperCase()));
            }
            case MONTHLY -> {
                if (dayOfMonth == null) {
                    throw new IllegalArgumentException("날짜를 입력해주세요");
                }
                return calculateMonthlyEndDate(startAt, totalDays, dayOfMonth);
            }
            default -> throw new IllegalArgumentException("지원하지 않습니다.");
        }
    }

    private static LocalDate calculateWeeklyEndDate(LocalDate starAt, long totalDays, DayOfWeek dayOfWeek) {
        long weeks = totalDays / 7;
        long remainDays = totalDays % 7;
        LocalDate endDate = starAt.plusDays(weeks);

        while (endDate.getDayOfWeek() != dayOfWeek) {
            endDate = endDate.plusDays(1);
        }
        return endDate.plusDays(remainDays);
    }

    private static LocalDate calculateMonthlyEndDate(LocalDate startAt, long totalDays, int dayOfMonth) {
        long months = totalDays / 30;
        long remainDays = totalDays % 30;
        LocalDate endDate = startAt.plusDays(months);

        endDate = endDate.withDayOfMonth(Math.min(dayOfMonth, endDate.lengthOfMonth()));
        return endDate.plusDays(remainDays);
    }



    // 수정 시 마김일 계산 로직
    public static LocalDate updateCalculateEndDate(LocalDate startAt, Long price, Long dayDeposit) {
        long calculateDay = calculatePrice(startAt, price, dayDeposit) / dayDeposit;
        LocalDate expirationDate = LocalDate.now().plusDays(calculateDay);
        return expirationDate;
    }

    // dayDeposit 수정 시 남은 금액 로직
    private static long calculatePrice(LocalDate startAt, Long price, Long dayDeposit) {
        long days = ChronoUnit.DAYS.between(startAt, LocalDate.now());
        long updatePrice =  price - (dayDeposit * days);
        return updatePrice;
    }
}
