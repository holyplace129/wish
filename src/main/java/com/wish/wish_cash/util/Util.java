package com.wish.wish_cash.util;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@RequiredArgsConstructor
public class Util {

    public static int calculateDate(LocalDate expirationAt) {
        return Period.between(LocalDate.now(), expirationAt).getDays();
    }
}
