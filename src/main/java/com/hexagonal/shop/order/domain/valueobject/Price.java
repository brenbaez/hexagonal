package com.hexagonal.shop.order.domain.valueobject;

import com.hexagonal.shop.shared.domain.valueobject.IntValueObject;

public class Price extends IntValueObject {
    public Price(Integer value) {
        super(value);
        // FIXME: 9/19/2021 ensure not negative value
    }
}
