package com.wish.wish_cash.common.util;

import com.wish.wish_cash.wish.domain.Frequency;

public class FrequencyUtil {

    // Frequency 별 적합한 일 수 계산
    public static long calculateTotalDays(Long currentAmount, Long dayDeposit, Frequency frequency) {
        return currentAmount / dayDeposit;
    }
}
