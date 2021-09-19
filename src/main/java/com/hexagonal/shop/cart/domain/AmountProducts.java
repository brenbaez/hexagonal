package com.hexagonal.shop.cart.domain;

import com.hexagonal.shop.shared.domain.valueobject.IntValueObject;

public class AmountProducts extends IntValueObject {
    public AmountProducts(Integer value) {
        super(value);
        ensureValidAmount(value);
    }

    private void ensureValidAmount(Integer value) {
        if (value <= 0)
            throw new IllegalArgumentException();
    }
}
