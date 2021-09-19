package com.hexagonal.demo.shop.domain.cart;

import com.hexagonal.demo.shop.domain.cart.valueobject.ProductId;
import lombok.Getter;

public class Product {
    @Getter
    private ProductId productId;
    public Product(ProductId productId) {
        this.productId = productId;
    }
}
