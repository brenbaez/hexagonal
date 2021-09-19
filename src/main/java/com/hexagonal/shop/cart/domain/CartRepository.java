package com.hexagonal.shop.cart.domain;

import com.hexagonal.shop.cart.domain.valueobject.CartId;

import java.util.Optional;

public interface CartRepository {

    Optional<Cart> get(CartId cartId);

    void save(Cart cart);
}
