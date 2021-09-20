package com.hexagonal.shop.cart.domain;

import com.hexagonal.shop.shared.domain.DomainError;
import com.hexagonal.shop.shared.domain.valueobject.CartId;

public class CartNotExist extends DomainError {

    public CartNotExist(CartId id) {
        super("cart_not_exist", String.format("The cart <%s> doesn't exist", id.value()));
    }
}
