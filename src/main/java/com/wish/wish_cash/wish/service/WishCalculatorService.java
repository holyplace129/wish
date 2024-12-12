package com.wish.wish_cash.wish.service;

import com.wish.wish_cash.common.util.DateUtil;
import com.wish.wish_cash.common.util.FrequencyUtil;
import com.wish.wish_cash.wish.domain.Frequency;
import com.wish.wish_cash.wish.presentation.dto.request.WishRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WishCalculatorService {

    public LocalDate calculateExpirationDate(WishRequest wishRequest) {
        long totalDays = FrequencyUtil.calculateTotalDays(
                wishRequest.getPrice(),
                wishRequest.getDayDeposit(),
                wishRequest.getFrequency()
        );
        return DateUtil.calculateEndDate(wishRequest.getStartAt(), totalDays, wishRequest.getFrequency());
    }
}
