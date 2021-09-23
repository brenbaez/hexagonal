package com.hexagonal.shop.shared.domain.valueobject;

import com.hexagonal.shop.shared.domain.exception.InvalidDiscountCode;

public class DiscountCode extends StringValueObject {
    public DiscountCode(String value) {
        super(value);
    }

    @Override
    protected void ensureValid(String value) {
        if (value.length() != 10)
            throw new InvalidDiscountCode(value);
    }

}
