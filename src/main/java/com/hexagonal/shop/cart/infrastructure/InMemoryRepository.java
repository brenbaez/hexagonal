package com.hexagonal.shop.cart.infrastructure;

import com.hexagonal.shop.cart.domain.Cart;
import com.hexagonal.shop.cart.domain.CartRepository;
import com.hexagonal.shop.cart.domain.valueobject.CartId;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryRepository implements CartRepository {
    private Map<String, Cart> carts = new HashMap<>();
    @Override
    public Optional<Cart> get(CartId cartId) {
        return Optional.ofNullable(carts.get(cartId.value()));
    }

    @Override
    public void save(Cart cart) {
        carts.put(cart.getId().value(), cart);
    }
}
