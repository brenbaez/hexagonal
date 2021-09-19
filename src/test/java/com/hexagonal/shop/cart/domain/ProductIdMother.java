package com.hexagonal.shop.cart.domain;

import com.hexagonal.shop.cart.domain.valueobject.ProductId;

import java.util.UUID;

public class ProductIdMother {

    public static ProductId random() {
        return new ProductId(UUID.randomUUID().toString());
    }
}
