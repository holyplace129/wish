package com.wish.wish_cash.wish.service;

import com.wish.wish_cash.common.util.DateUtil;
import com.wish.wish_cash.common.util.FrequencyUtil;
import com.wish.wish_cash.wish.domain.Wish;
import com.wish.wish_cash.wish.domain.repository.WishRepository;
import com.wish.wish_cash.wish.presentation.dto.request.WishRequest;
import com.wish.wish_cash.wish.presentation.dto.request.WishUpdateRequest;
import com.wish.wish_cash.wish.presentation.dto.response.WishDetailResponse;
import com.wish.wish_cash.wish.presentation.dto.response.WishResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishServiceV2 {

    private final WishRepository wishRepository;

    // 전체 조회
    public List<WishResponse> getAllWishList(Pageable pageable) {
        return wishRepository.findAll(pageable).stream()
                .map(wish -> WishResponse.from(wish))
                .collect(Collectors.toList());
    }

    // 단일 조회
    public WishDetailResponse findById(Integer id) {
        return wishRepository.findById(id)
                .map(WishDetailResponse::of)
                .orElseThrow(() -> new IllegalArgumentException("위시리스트를 찾을 수 없습니다."));
    }

    // 생성
    @Transactional
    public WishDetailResponse createWish(WishRequest wishRequest) {
        LocalDate expirationDate = calculateEndDate(wishRequest);

        Wish wish = Wish.builder()
                .title(wishRequest.getTitle())
                .content(wishRequest.getContent())
                .image(wishRequest.getImage())
                .price(wishRequest.getPrice())
                .currentAmount(wishRequest.getPrice())
                .dayDeposit(wishRequest.getDayDeposit())
                .frequency(wishRequest.getFrequency())
                .startAt(wishRequest.getStartAt())
                .expirationAt(expirationDate)
                .createAt(LocalDate.now())
                .build();

        Wish saveWish = wishRepository.save(wish);
        return WishDetailResponse.of(saveWish);
    }

    // 수정
    public WishDetailResponse updateWish(Integer id, WishUpdateRequest wishUpdateRequest) {
        Wish wish = wishRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("위시리스트를 찾을 수 없습니다."));
        if (wishUpdateRequest.getDayDeposit() > wish.getCurrentAmount()) {
            throw new IllegalArgumentException("입금액은 현제 금액보다 클 수 없습니다.");
        } else {
            wish.updateWish(wishUpdateRequest);
            return WishDetailResponse.of(wishRepository.save(wish));
        }
    }

    // 삭제
    public void deleteWish(Integer id) {
        Wish wish = wishRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("위시리스트를 찾을 수 없습니다."));

        wishRepository.delete(wish);
    }

    // 금액 수정
    public WishDetailResponse updateCurrentAmount(Integer id) {
        Wish wish = wishRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("위시리스트를 찾을 수 없습니다."));

        Long newCurrentAmount = wish.getCurrentAmount() - wish.getDayDeposit();
        wish.updateCurrentAmount(newCurrentAmount);

        return WishDetailResponse.of(wishRepository.save(wish));
    }

    private LocalDate calculateEndDate(WishRequest wishRequest) {
        long totalDays = FrequencyUtil.calculateTotalDays(
                wishRequest.getPrice(),
                wishRequest.getDayDeposit(),
                wishRequest.getFrequency()
        );
        return DateUtil.calculateEndDate(wishRequest.getStartAt(), totalDays, wishRequest.getFrequency());
    }
}
