package com.wish.wish_cash.wish.service;

import com.wish.wish_cash.util.Util;
import com.wish.wish_cash.wish.domain.Wish;
import com.wish.wish_cash.wish.domain.repository.WishRepository;
import com.wish.wish_cash.wish.presentation.dto.WishDetailResponse;
import com.wish.wish_cash.wish.presentation.dto.WishResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishService {

    private final WishRepository wishRepository;

    // 리스트 전체 조회
    public List<WishResponse> getAllWishList(Pageable pageable) {
        return wishRepository.findAll().stream()
                .map(wish -> new WishResponse(
                        wish.getId(),
                        wish.getTitle(),
                        wish.getContent(),
                        wish.getImage(),
                        Util.calculateDate(wish.getExpirationAt())
                )).collect(Collectors.toList());
    }

    public WishDetailResponse findById(Integer id) {
        Optional<Wish> wishDetail = wishRepository.findById(id);

        if (wishDetail.isPresent()) {
            return WishDetailResponse.of(wishDetail.get());
        } else {
            throw new IllegalArgumentException();
        }
    }
}
