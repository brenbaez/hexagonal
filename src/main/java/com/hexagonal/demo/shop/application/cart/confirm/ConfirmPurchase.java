package com.hexagonal.demo.shop.application.cart.confirm;

import com.hexagonal.demo.shared.domain.bus.event.EventBus;
import com.hexagonal.demo.shop.domain.cart.Cart;
import com.hexagonal.demo.shop.domain.cart.CartRepository;
import com.hexagonal.demo.shop.domain.cart.valueobject.Address;
import com.hexagonal.demo.shop.domain.cart.valueobject.CartId;
import com.hexagonal.demo.shop.domain.cart.valueobject.DiscountCode;
import com.hexagonal.demo.shop.domain.cart.valueobject.Email;

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
        return cartRepository.get(cartId)
                .orElseThrow(() -> new RuntimeException("Cart does not exist"));
    }
}
