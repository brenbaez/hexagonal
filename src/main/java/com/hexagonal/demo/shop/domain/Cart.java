package com.hexagonal.demo.shop.domain;

import java.util.HashMap;

public class Cart {

    private final CartId id;
    private CartDetail cartDetail;

    public Cart(CartId cartId) {
        this.id = cartId;
        cartDetail = new CartDetail(new HashMap<>());
    }

    public void add(ProductId productId, AmountProducts amount) {
        cartDetail = cartDetail.withNewProducts(productId, amount);
    }

    public CartDetail getDetail() {
        return cartDetail;
    }

    public CartId getId() {
        return id;
    }
}
