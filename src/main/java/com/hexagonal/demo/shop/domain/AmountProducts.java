package com.hexagonal.demo.shop.domain;

import com.hexagonal.demo.shared.domain.IntValueObject;

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
