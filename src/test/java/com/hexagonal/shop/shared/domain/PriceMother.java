package com.hexagonal.shop.shared.domain;

import com.hexagonal.shop.order.domain.valueobject.Price;

import java.util.Random;

public class PriceMother {

    public static Price randomPrice() {
        Random random = new Random();
        return new Price(random.nextInt(100000) + 1);
    }

}
