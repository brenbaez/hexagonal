package com.hexagonal.shop.cart.domain.exception;

import com.hexagonal.shop.shared.domain.DomainError;

public class InvalidCartState extends DomainError {

    public InvalidCartState(String value) {
        super("invalid_cart_state", String.format("Cart is in state: <%s>", value));
    }
}
