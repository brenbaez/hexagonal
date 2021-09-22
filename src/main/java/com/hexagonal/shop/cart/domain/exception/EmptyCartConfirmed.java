package com.hexagonal.shop.cart.domain.exception;

import com.hexagonal.shop.shared.domain.DomainError;

public class EmptyCartConfirmed extends DomainError {

    public EmptyCartConfirmed() {
        super("empty_cart_confirmed","Cannot confirm an empty cart");
    }
}
