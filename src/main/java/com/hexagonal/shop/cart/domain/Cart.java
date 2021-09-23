package com.hexagonal.shop.cart.domain;

import com.hexagonal.shop.cart.domain.exception.InvalidCartState;
import com.hexagonal.shop.cart.domain.exception.EmptyCartConfirmed;
import com.hexagonal.shop.shared.domain.AggregateRoot;
import com.hexagonal.shop.shared.domain.cart.PurchaseConfirmedDomainEvent;
import com.hexagonal.shop.shared.domain.valueobject.Address;
import com.hexagonal.shop.shared.domain.valueobject.CartId;
import com.hexagonal.shop.shared.domain.valueobject.DiscountCode;
import com.hexagonal.shop.shared.domain.valueobject.Email;
import com.hexagonal.shop.shared.domain.valueobject.ProductId;

import java.util.HashMap;

public class Cart extends AggregateRoot {

    private final CartId id;
    private CartDetail cartDetail;
    private CartState state;

    public Cart(CartId cartId) {
        this.id = cartId;
        cartDetail = new CartDetail(new HashMap<>());
        state = CartState.OPEN;
    }

    public void add(ProductId productId, ProductQuantity amount) {
        ensureHasState(CartState.OPEN);
        cartDetail = cartDetail.withNewProducts(productId, amount);
    }

    private void ensureHasState(CartState stateToCheck) {
        if (stateToCheck != state)
            throw new InvalidCartState(state.name());
    }

    public CartDetail getDetail() {
        return cartDetail;
    }

    public CartId getId() {
        return id;
    }

    public boolean hasProducts() {
        return !cartDetail.total().equals(0);
    }

    public void confirm(DiscountCode discountCode, Email email, Address address) {
        ensureCanBeConfirmed();
        this.state = CartState.CONFIRMED;
        record(new PurchaseConfirmedDomainEvent(id, getDetail().getProducts(), discountCode, email, address));
    }

    private void ensureCanBeConfirmed() {
        if (!hasProducts())
            throw new EmptyCartConfirmed();
        ensureHasState(CartState.OPEN);
    }

    public boolean isConfirmed() {
        return CartState.CONFIRMED == state;
    }
}
