package com.hexagonal.demo.shop.domain;

import java.util.UUID;

public class ProductIdMother {

    public static ProductId random() {
        return new ProductId(UUID.randomUUID().toString());
    }
}
