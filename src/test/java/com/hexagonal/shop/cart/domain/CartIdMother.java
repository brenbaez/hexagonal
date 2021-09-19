package com.hexagonal.shop.cart.domain;

import com.hexagonal.shop.cart.domain.valueobject.CartId;

import java.util.UUID;

public class CartIdMother {

    public static CartId random() {
        return new CartId(UUID.randomUUID().toString());
    }
}
