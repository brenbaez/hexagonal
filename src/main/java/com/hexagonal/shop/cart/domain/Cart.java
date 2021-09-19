package com.hexagonal.shop.cart.domain;

import com.hexagonal.shop.shared.domain.AggregateRoot;
import com.hexagonal.shop.shared.domain.cart.PurchaseConfirmedDomainEvent;
import com.hexagonal.shop.cart.domain.valueobject.Address;
import com.hexagonal.shop.cart.domain.valueobject.CartDetail;
import com.hexagonal.shop.cart.domain.valueobject.CartId;
import com.hexagonal.shop.cart.domain.valueobject.DiscountCode;
import com.hexagonal.shop.cart.domain.valueobject.Email;
import com.hexagonal.shop.cart.domain.valueobject.ProductId;

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

    public void add(ProductId productId, AmountProducts amount) {
        ensureHasState(CartState.OPEN);
        cartDetail = cartDetail.withNewProducts(productId, amount);
    }

    private void ensureHasState(CartState stateToCheck) {
        if (stateToCheck != state)
            throw new IllegalStateException("Cart is in state " + state);
    }

    // TODO: 9/18/2021 maybe this method could have another name? 
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
            throw new IllegalStateException("Cannot confirm an empty cart");
        ensureHasState(CartState.OPEN);
    }

    public boolean isConfirmed(){
        return CartState.CONFIRMED == state;
    }
}
