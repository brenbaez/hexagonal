package com.hexagonal.demo.shop.domain.cart.valueobject;

import com.hexagonal.demo.shared.domain.Identifier;

public class ProductId extends Identifier {
    public ProductId(String value) {
        super(value);
    }
}
