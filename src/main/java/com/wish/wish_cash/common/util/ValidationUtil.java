package com.wish.wish_cash.common.util;

public class ValidationUtil {

    public static void validateNonNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
