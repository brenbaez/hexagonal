package com.hexagonal.demo.shop.domain.order.valueobject;

import com.hexagonal.demo.shared.domain.Identifier;

public class OrderLineId extends Identifier {
    public OrderLineId(String value) {
        super(value);
    }
}
