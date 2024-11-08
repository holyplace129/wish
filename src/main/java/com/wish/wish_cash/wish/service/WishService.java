package com.wish.wish_cash.wish.service;

import com.wish.wish_cash.common.util.Util;
import com.wish.wish_cash.wish.domain.Wish;
import com.wish.wish_cash.wish.domain.repository.WishRepository;
import com.wish.wish_cash.wish.presentation.dto.WishUpdateRequest;
import com.wish.wish_cash.wish.presentation.dto.WishDetailResponse;
import com.wish.wish_cash.wish.presentation.dto.WishRequest;
import com.wish.wish_cash.wish.presentation.dto.WishResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishService {

    private final WishRepository wishRepository;

    // 리스트 전체 조회
    public List<WishResponse> getAllWishList(Pageable pageable) {
        return wishRepository.findAll(pageable).stream()
                .map(wish -> new WishResponse(
                        wish.getId(),
                        wish.getTitle(),
                        wish.getContent(),
                        wish.getImage(),
                        wish.getStartAt(),
                        Util.calculateDate(wish.getExpirationAt())
                )).collect(Collectors.toList());
    }

    // 위시 리스트 단일 조회
    public WishDetailResponse findById(Integer id) {
        Optional<Wish> wishDetail = wishRepository.findById(id);

        if (wishDetail.isPresent()) {
            return WishDetailResponse.of(wishDetail.get());
        } else {
            throw new IllegalArgumentException("위시리스트를 찾을 수 없습니다.");
        }
    }

    // 위시 리스트 생성
    public WishDetailResponse createWish(WishRequest wishRequest) {
        Wish wish = Wish.builder()
                .title(wishRequest.getTitle())
                .content(wishRequest.getContent())
                .image(wishRequest.getImage())
                .price(wishRequest.getPrice())
                .dayDeposit(wishRequest.getDayDeposit())
                .startAt(wishRequest.getStartAt())
                .frequency(wishRequest.getFrequency())
                .expirationAt(Util.calculateEndDate(wishRequest.getStartAt(), wishRequest.getPrice(), wishRequest.getDayDeposit(), wishRequest.getFrequency()))
                .createAt(LocalDate.now())
                .build();

        Wish saveWish = wishRepository.save(wish);
        return WishDetailResponse.of(saveWish);
    }

    // 위시 리스트 수정
    public WishDetailResponse updateWish(Integer id, WishUpdateRequest wishUpdateRequest) {
        Optional<Wish> wishId = wishRepository.findById(id);

        if (wishId.isPresent()) {
            Wish wish = wishId.get();

            wish.updateWish(wishUpdateRequest);
            Wish updateWish = wishRepository.save(wish);
            return WishDetailResponse.of(updateWish);
        } else {
            throw new IllegalArgumentException("위시리스트를 찾을 수 없습니다.");
        }
    }
}
