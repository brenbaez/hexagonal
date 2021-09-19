package com.hexagonal.demo.shop.domain.order.valueobject;

import com.hexagonal.demo.shared.domain.IntValueObject;

public class Price extends IntValueObject {
    public Price(Integer value) {
        super(value);
    }
}
