package com.wish.wish_cash.wish.presentation;

import com.wish.wish_cash.wish.presentation.dto.response.WishDetailResponse;
import com.wish.wish_cash.wish.presentation.dto.request.WishRequest;
import com.wish.wish_cash.wish.presentation.dto.response.WishResponse;
import com.wish.wish_cash.wish.presentation.dto.request.WishUpdateRequest;
import com.wish.wish_cash.wish.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wish")
public class WishApi {

    private final WishService wishService;

    // 리스트 전체 조회
    @GetMapping("")
    public ResponseEntity<List<WishResponse>> getAllWishList(@PageableDefault(sort = "id", size = 6) Pageable pageable) {
        List<WishResponse> wishResponseList = wishService.getAllWishList(pageable);

        return ResponseEntity.ok(wishResponseList);
    }

    // 위시 단일 조회
    @GetMapping("/{id}")
    public ResponseEntity<WishDetailResponse> getWishDetail(@PathVariable Integer id) {
        WishDetailResponse wishId = wishService.findById(id);
        return ResponseEntity.ok(wishId);
    }

    // 위시리스트 생성
    @PostMapping("")
    public ResponseEntity<WishDetailResponse> createWish(@RequestBody WishRequest wishRequest) {
        WishDetailResponse wishResponse = wishService.createWish(wishRequest);
        return ResponseEntity.ok(wishResponse);
    }

    // 위시리스트 수정
    @PatchMapping("/{id}")
    public ResponseEntity<WishDetailResponse> updateWish(@PathVariable Integer id, @RequestBody WishUpdateRequest wishUpdateRequest) {
        WishDetailResponse wishResponse = wishService.updateWish(id, wishUpdateRequest);
        return ResponseEntity.ok(wishResponse);
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
        WishDetailResponse wishId = wishService.updateCurrentAmount(id);
        return ResponseEntity.ok(wishId);
    }
}
