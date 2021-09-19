package com.hexagonal.demo.shop.domain;

import com.hexagonal.demo.shop.domain.cart.Product;

public class ProductMother {

    public static Product empty() {
        return new Product(ProductIdMother.random());
    }
}
