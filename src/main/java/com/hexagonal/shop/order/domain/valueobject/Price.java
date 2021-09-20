package com.hexagonal.shop.order.domain.valueobject;

import com.hexagonal.shop.shared.domain.valueobject.IntValueObject;

public class Price extends IntValueObject {

    public Price(Integer value) {
        super(value);
    }

    @Override
    protected void ensureValid(Integer value) {
        if (value <= 0)
            throw new IllegalArgumentException("Price cannot be less than 1");
    }
}
