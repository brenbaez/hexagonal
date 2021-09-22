package com.hexagonal.shop.shared.domain;

import com.hexagonal.shop.cart.domain.ProductQuantity;

import java.util.Random;

public class ProductQuantityMother {

    public static ProductQuantity randomProductQuantity() {
        Random random = new Random();
        return new ProductQuantity(random.nextInt(100) + 1);
    }

}
