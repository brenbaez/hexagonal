package com.hexagonal.shop.cart.application.confirm;

import com.hexagonal.shop.shared.domain.bus.event.EventBus;
import com.hexagonal.shop.cart.domain.Cart;
import com.hexagonal.shop.cart.domain.CartRepository;
import com.hexagonal.shop.shared.domain.valueobject.Address;
import com.hexagonal.shop.shared.domain.valueobject.CartId;
import com.hexagonal.shop.shared.domain.valueobject.DiscountCode;
import com.hexagonal.shop.shared.domain.valueobject.Email;

import java.util.Optional;

public class ConfirmPurchase {

    private final CartRepository cartRepository;
    private final EventBus eventBus;

    public ConfirmPurchase(CartRepository cartRepository, EventBus eventBus) {
        this.cartRepository = cartRepository;
        this.eventBus = eventBus;
    }

    public void confirm(CartId cartId, DiscountCode discountCode, Email email, Address address){
        Cart cart = getCart(cartId);
        cart.confirm(discountCode, email, address);
        cartRepository.save(cart);
        eventBus.publish(cart.pullDomainEvents());
    }

    private Cart getCart(CartId cartId) {
        Optional<Cart> cart = cartRepository.get(cartId);
        if(cart.isEmpty())
            throw new RuntimeException("Cart does not exist");
        return cart.get();
    }
}
