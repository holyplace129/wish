package com.wish.wish_cash.wish.presentation;

import com.wish.wish_cash.wish.presentation.dto.WishDetailResponse;
import com.wish.wish_cash.wish.presentation.dto.WishResponse;
import com.wish.wish_cash.wish.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wish")
public class WishApi {

    private final WishService wishService;

    // 리스트 전체 조회
    @GetMapping("")
    public ResponseEntity<List<WishResponse>> getAllWishList(@PageableDefault(sort = "id", size = 1, page = 6) Pageable pageable) {
        List<WishResponse> wishResponseList = wishService.getAllWishList(pageable);

        return ResponseEntity.ok(wishResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WishDetailResponse> getWishDetail(@PathVariable Integer id) {
        WishDetailResponse wishId = wishService.findById(id);
        return ResponseEntity.ok(wishId);
    }
}
