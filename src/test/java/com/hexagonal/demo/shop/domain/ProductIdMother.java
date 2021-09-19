package com.hexagonal.demo.shop.domain;

import com.hexagonal.demo.shop.domain.cart.valueobject.ProductId;

import java.util.UUID;

public class ProductIdMother {

    public static ProductId random() {
        return new ProductId(UUID.randomUUID().toString());
    }
}
