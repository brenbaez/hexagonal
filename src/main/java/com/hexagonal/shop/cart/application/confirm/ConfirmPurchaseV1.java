package com.hexagonal.shop.cart.application.confirm;

import com.hexagonal.shop.cart.domain.Cart;
import com.hexagonal.shop.cart.domain.exception.CartNotExist;
import com.hexagonal.shop.cart.domain.CartRepository;
import com.hexagonal.shop.shared.domain.valueobject.Address;
import com.hexagonal.shop.shared.domain.valueobject.CartId;
import com.hexagonal.shop.shared.domain.valueobject.DiscountCode;
import com.hexagonal.shop.shared.domain.valueobject.Email;

public class ConfirmPurchaseV1 {

    private final CartRepository cartRepository;

    public ConfirmPurchaseV1(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void confirm(CartId cartId, DiscountCode discountCode, Email email, Address address) {
        Cart cart = getCart(cartId);
        cart.confirm(discountCode, email, address);
        cartRepository.save(cart);
    }

    private Cart getCart(CartId cartId) {
        return cartRepository.get(cartId).orElseThrow(() -> new CartNotExist(cartId));
    }
}
