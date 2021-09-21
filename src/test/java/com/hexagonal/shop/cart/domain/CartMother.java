package com.hexagonal.shop.cart.domain;

import java.util.Random;

public class CartMother {

    public static Cart empty() {
        return new Cart(CartIdMother.random());
    }

    public static Cart withData() {
        Cart cart = new Cart(CartIdMother.random());
        Random random = new Random();
        cart.add(ProductIdMother.random(), new ProductQuantity(random.nextInt(100) + 1)); // FIXME: 9/18/2021 can be  a random value
        cart.add(ProductIdMother.random(), new ProductQuantity(random.nextInt(100) + 1));
        return cart;
    }
}
