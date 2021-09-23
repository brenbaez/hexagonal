package com.hexagonal.shop.cart.domain.exception;

import com.hexagonal.shop.shared.domain.DomainError;

public class EmptyCartCannotBeConfirmed extends DomainError {

    public EmptyCartCannotBeConfirmed() {
        super("empty_cart_confirmed","Cannot confirm an empty cart");
    }
}
