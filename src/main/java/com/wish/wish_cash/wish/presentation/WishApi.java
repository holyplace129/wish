package com.wish.wish_cash.wish.presentation;

import com.wish.wish_cash.wish.presentation.dto.WishDetailResponse;
import com.wish.wish_cash.wish.presentation.dto.WishRequest;
import com.wish.wish_cash.wish.presentation.dto.WishResponse;
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
    @PostMapping("/wish")
    public ResponseEntity<WishResponse> createWish(@RequestBody WishRequest wishRequest) {
        WishResponse wishResponse = wishService.createWish(wishRequest);
        return ResponseEntity.ok(wishResponse);
    }
}
