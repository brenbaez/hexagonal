package com.hexagonal.shop.cart.infrastructure.persistence;

import com.hexagonal.shop.cart.domain.Cart;
import com.hexagonal.shop.cart.domain.CartRepository;
import com.hexagonal.shop.shared.domain.valueobject.CartId;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository("cachedCartRepository")
public class CachedCartRepository implements CartRepository {

    private Map<String, Cart> cachedCarts = new HashMap<>();

    private CartRepository cartRepository;

    public CachedCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Optional<Cart> get(CartId cartId) {
        var cachedCart = cachedCarts.get(cartId.value());
        if (cachedCart != null)
            return Optional.of(cachedCart);

        Optional<Cart> cachedCartOpt = cartRepository.get(cartId);

        cachedCartOpt.ifPresent(cart -> cachedCarts.put(cartId.value(), cart));

        return cachedCartOpt;
    }

    @Override
    public void save(Cart cart) {
        cachedCarts.put(cart.getId().value(), cart);
        cartRepository.save(cart);
    }
}
