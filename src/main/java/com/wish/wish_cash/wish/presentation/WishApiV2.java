package com.wish.wish_cash.wish.presentation;

import com.wish.wish_cash.wish.presentation.dto.request.WishRequest;
import com.wish.wish_cash.wish.presentation.dto.request.WishUpdateRequest;
import com.wish.wish_cash.wish.presentation.dto.response.WishDetailResponse;
import com.wish.wish_cash.wish.presentation.dto.response.WishResponse;
import com.wish.wish_cash.wish.service.WishCalculatorService;
import com.wish.wish_cash.wish.service.WishServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("wish/v2")
public class WishApiV2 {

    private final WishServiceV2 wishService;
    private final WishCalculatorService wishCalculatorService;

    // 전체 조회
    @GetMapping("")
    public ResponseEntity<List<WishResponse>> getAllWishList(
            @PageableDefault(sort = "id", size = 6) Pageable pageable) {
        List<WishResponse> wishResponseList = wishService.getAllWishList(pageable);
        return ResponseEntity.ok(wishResponseList);
    }

    // 단일 조회
    @GetMapping("/{id}")
    public ResponseEntity<WishDetailResponse> getWishDetail(@PathVariable Integer id) {
        WishDetailResponse wishDetail = wishService.findById(id);
        return ResponseEntity.ok(wishDetail);
    }

    // 위시리스트 생성
    @PostMapping("")
    public ResponseEntity<WishDetailResponse> createWish(@RequestBody WishRequest wishRequest) {
        // 만료일 계산은 WishCalculatorService에서 처리
        LocalDate expirationAt = wishCalculatorService.calculateExpirationDate(wishRequest);

        // Service에서 처리할 수 있도록 만료일 추가
        WishDetailResponse createdWish = wishService.createWish(
                wishRequest.toBuilder().expirationAt(expirationAt).build()
        );

        return ResponseEntity.ok(createdWish);
    }

    // 위시리스트 수정
    @PatchMapping("/{id}")
    public ResponseEntity<WishDetailResponse> updateWish(
            @PathVariable Integer id,
            @RequestBody WishUpdateRequest wishUpdateRequest) {
        WishDetailResponse updatedWish = wishService.updateWish(id, wishUpdateRequest);
        return ResponseEntity.ok(updatedWish);
    }

    // 위시리스트 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWish(@PathVariable Integer id) {
        wishService.deleteWish(id);
        return ResponseEntity.noContent().build();
    }

    // 금액 수정
    @PutMapping("/amount/{id}")
    public ResponseEntity<WishDetailResponse> updateCurrentAmount(@PathVariable Integer id) {
        WishDetailResponse updatedWish = wishService.updateCurrentAmount(id);
        return ResponseEntity.ok(updatedWish);
    }
}
