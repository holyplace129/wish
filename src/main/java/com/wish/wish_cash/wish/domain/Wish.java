package com.wish.wish_cash.wish.domain;

import com.wish.wish_cash.common.util.DateUtil;
import com.wish.wish_cash.common.util.FrequencyUtil;
import com.wish.wish_cash.common.util.Util;
import com.wish.wish_cash.wish.presentation.dto.request.WishUpdateRequest;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@RequiredArgsConstructor
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String content;

    private String image;

    private Long price;

    private Long currentAmount;

    private Long dayDeposit;

    private LocalDate createAt;

    private LocalDate startAt;

    private LocalDate modifyAt;

    private LocalDate expirationAt;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    @Builder
    public Wish(String title, String content, String image, Long price, Long currentAmount, Long dayDeposit, LocalDate createAt, LocalDate startAt, LocalDate modifyAt, LocalDate expirationAt, Frequency frequency) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.price = price;
        this.currentAmount = currentAmount;
        this.dayDeposit = dayDeposit;
        this.createAt = createAt;
        this.startAt = startAt;
        this.modifyAt = modifyAt;
        this.expirationAt = expirationAt;
        this.frequency = frequency;
    }

    public void updateCurrentAmount(Long currentAmount) {
        this.currentAmount = currentAmount;
    }

    public void updateWish(WishUpdateRequest wishUpdateRequest) {
        // null 체크를 통해 기존 값 유지
        this.title = wishUpdateRequest.getTitle() != null ? wishUpdateRequest.getTitle() : this.title;
        this.content = wishUpdateRequest.getContent() != null ? wishUpdateRequest.getContent() : this.content;
        this.image = wishUpdateRequest.getImage() != null ? wishUpdateRequest.getImage() : this.image;
        this.price = wishUpdateRequest.getPrice() != null ? wishUpdateRequest.getPrice() : this.price;
        this.dayDeposit = wishUpdateRequest.getDayDeposit() != null ? wishUpdateRequest.getDayDeposit() : this.dayDeposit;
        this.frequency = wishUpdateRequest.getFrequency() != null ? wishUpdateRequest.getFrequency() : this.frequency;
        this.startAt = wishUpdateRequest.getStartAt() != null ? wishUpdateRequest.getStartAt() : this.startAt;
        this.modifyAt = LocalDate.now();

        // price와 dayDeposit이 null인 경우 totalDays 계산 생략
        if (this.price != null && this.dayDeposit != null) {
            long totalDays = FrequencyUtil.calculateTotalDays(this.price, this.dayDeposit, this.frequency);
            this.expirationAt = DateUtil.calculateEndDate(this.getStartAt(), totalDays, this.getFrequency());
        }
    }
}
