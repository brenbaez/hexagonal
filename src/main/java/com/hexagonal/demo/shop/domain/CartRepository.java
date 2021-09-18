package com.hexagonal.demo.shop.domain;

import java.util.Optional;

public interface CartRepository {

    Optional<Cart> get(CartId cartId);

    void save(Cart cart);
}
