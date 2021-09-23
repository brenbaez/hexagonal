package com.hexagonal.shop.cart.domain;

import com.hexagonal.shop.shared.domain.valueobject.CartId;

public class CartPojo {

    private CartId id;
    private CartDetail cartDetail;
    private CartState state;

    public CartId getId() {
        return id;
    }

    public void setId(CartId id) {
        this.id = id;
    }

    public CartDetail getCartDetail() {
        return cartDetail;
    }

    public void setCartDetail(CartDetail cartDetail) {
        this.cartDetail = cartDetail;
    }

    public CartState getState() {
        return state;
    }

    public void setState(CartState state) {
        this.state = state;
    }
}
