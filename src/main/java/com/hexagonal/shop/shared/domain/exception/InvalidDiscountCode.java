package com.hexagonal.shop.shared.domain.exception;

import com.hexagonal.shop.shared.domain.DomainError;

public class InvalidDiscountCode extends DomainError {
    public InvalidDiscountCode(String value) {
        super("invalid_discount", String.format("<%s> is not a valid discount", value));
    }
}
