package com.hexagonal.demo.shop.domain;

import java.util.UUID;

public class CartIdMother {

    public static CartId random() {
        return new CartId(UUID.randomUUID().toString());
    }
}
