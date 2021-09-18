package com.hexagonal.demo.shop.domain;

import lombok.Getter;

public class Product {
    @Getter
    private ProductId productId;
    public Product(ProductId productId) {
        this.productId = productId;
    }
}
