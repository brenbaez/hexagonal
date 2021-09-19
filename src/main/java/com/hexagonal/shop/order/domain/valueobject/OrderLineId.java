package com.hexagonal.shop.order.domain.valueobject;

import com.hexagonal.shop.shared.domain.valueobject.Identifier;

public class OrderLineId extends Identifier {
    public OrderLineId(String value) {
        super(value);
    }
}
