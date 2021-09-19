package com.hexagonal.demo.shop.domain.cart;

import com.hexagonal.demo.shop.domain.cart.valueobject.CartId;

import java.util.Optional;

public interface CartRepository {

    Optional<Cart> get(CartId cartId);

    void save(Cart cart);
}
