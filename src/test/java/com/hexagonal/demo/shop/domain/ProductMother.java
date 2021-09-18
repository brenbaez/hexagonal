package com.hexagonal.demo.shop.domain;

public class ProductMother {

    public static Product empty() {
        return new Product(ProductIdMother.random());
    }
}
