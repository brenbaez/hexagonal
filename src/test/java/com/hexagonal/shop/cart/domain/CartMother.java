package com.hexagonal.shop.cart.domain;

import com.hexagonal.shop.shared.domain.ProductIdMother;
import com.hexagonal.shop.shared.domain.ProductQuantityMother;

public class CartMother {

    public static Cart empty() {
        return new Cart(CartIdMother.random());
    }

    public static Cart withData() {
        Cart cart = new Cart(CartIdMother.random());
        cart.add(ProductIdMother.random(), ProductQuantityMother.randomProductQuantity());
        cart.add(ProductIdMother.random(), ProductQuantityMother.randomProductQuantity());
        return cart;
    }
}
