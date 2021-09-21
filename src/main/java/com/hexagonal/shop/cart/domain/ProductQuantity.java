package com.hexagonal.shop.cart.domain;

import com.hexagonal.shop.shared.domain.valueobject.IntValueObject;

public class ProductQuantity extends IntValueObject {
    public ProductQuantity(Integer value) {
        super(value);
    }

    @Override
    protected void ensureValid(Integer value) {
        if (value <= 0)
            throw new IllegalArgumentException("Products quantity cannot be negative or zero");
    }
}
