package com.wish.wish_cash.wish.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Long dayDeposit;
    private LocalDate createAt;
    private LocalDate expirationAt;

    @Builder
    public Wish(String title, String content, String image, Long price, Long dayDeposit, LocalDate createAt, LocalDate expirationAt) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.price = price;
        this.dayDeposit = dayDeposit;
        this.createAt = createAt;
        this.expirationAt = expirationAt;
    }
}
