package com.hexagonal.shop.cart.domain;

public class CartMother {

    public static Cart empty() {
        return new Cart(CartIdMother.random());
    }

    public static Cart withData() {
        Cart cart = new Cart(CartIdMother.random());
        cart.add(ProductIdMother.random(), new ProductQuantity(5)); // FIXME: 9/18/2021 can be  a random value
        cart.add(ProductIdMother.random(), new ProductQuantity(6));
        return cart;
    }
}
