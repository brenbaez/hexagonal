package com.hexagonal.demo.shop.domain;

import com.hexagonal.demo.shop.domain.cart.AmountProducts;
import com.hexagonal.demo.shop.domain.cart.Cart;

public class CartMother {

    public static Cart empty() {
        return new Cart(CartIdMother.random());
    }

    public static Cart withData() {
        Cart cart = new Cart(CartIdMother.random());
        cart.add(ProductIdMother.random(), new AmountProducts(5)); // FIXME: 9/18/2021 can be  a random value
        cart.add(ProductIdMother.random(), new AmountProducts(6));
        return cart;
    }
}
