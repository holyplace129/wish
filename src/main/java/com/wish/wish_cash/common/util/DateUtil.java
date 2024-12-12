package com.wish.wish_cash.common.util;

import com.wish.wish_cash.wish.domain.Frequency;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtil {

    // D-day 계산 로직
    public static long calculateDate(LocalDate expirationAt) {
        return ChronoUnit.DAYS.between(LocalDate.now(), expirationAt);
    }

    // 마감일 계산 로직
    public static LocalDate calculateEndDate(LocalDate startAt, long totalDays, Frequency frequency) {
        return switch (frequency) {
            case DAILY -> startAt.plusDays(totalDays);
            case WEEKLY -> startAt.plusWeeks(totalDays);
            case MONTHLY -> startAt.plusMonths(totalDays);
            default -> throw new IllegalArgumentException("지원하지 않는 주기입니다.");
        };
    }

//    // 업데이트 마감일 계산 로직
//    public static LocalDate calculateUpdateEndDate(LocalDate startAt,)
}
