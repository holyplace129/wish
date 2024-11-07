package com.wish.wish_cash.wish.service;

import com.wish.wish_cash.util.Util;
import com.wish.wish_cash.wish.domain.Wish;
import com.wish.wish_cash.wish.domain.repository.WishRepository;
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
                        Util.calculateDate(wish.getExpirationAt())
                )).collect(Collectors.toList());
    }

    // 위시 리스트 단일 조회
    public WishDetailResponse findById(Integer id) {
        Optional<Wish> wishDetail = wishRepository.findById(id);

        if (wishDetail.isPresent()) {
            return WishDetailResponse.of(wishDetail.get());
        } else {
            throw new IllegalArgumentException();
        }
    }

    // 위시 리스트 생성
    public WishResponse createWish(WishRequest wishRequest) {
        Wish wish = Wish.builder()
                .title(wishRequest.getTitle())
                .content(wishRequest.getContent())
                .image(wishRequest.getImage())
                .price(wishRequest.getPrice())
                .dayDeposit(wishRequest.getDayDeposit())
                .expirationAt(Util.calculateEndDate(wishRequest.getPrice(), wishRequest.getDayDeposit()))
                .createAt(LocalDate.now())
                .build();

        Wish saveWish = wishRepository.save(wish);
        return WishResponse.from(saveWish);
    }
}
