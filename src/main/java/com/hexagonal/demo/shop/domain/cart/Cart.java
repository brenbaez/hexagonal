package com.hexagonal.demo.shop.domain.cart;

import com.hexagonal.demo.shared.domain.AggregateRoot;
import com.hexagonal.demo.shared.domain.cart.PurchaseConfirmedDomainEvent;
import com.hexagonal.demo.shop.domain.cart.valueobject.Address;
import com.hexagonal.demo.shop.domain.cart.valueobject.CartDetail;
import com.hexagonal.demo.shop.domain.cart.valueobject.CartId;
import com.hexagonal.demo.shop.domain.cart.valueobject.DiscountCode;
import com.hexagonal.demo.shop.domain.cart.valueobject.Email;
import com.hexagonal.demo.shop.domain.cart.valueobject.ProductId;

import java.util.HashMap;

public class Cart extends AggregateRoot {

    private final CartId id;
    private CartDetail cartDetail;
    private CartState state;

    public Cart(CartId cartId) {
        this.id = cartId;
        cartDetail = new CartDetail(new HashMap<>());
    }

    public void add(ProductId productId, AmountProducts amount) {
        ensureHasState(CartState.OPEN);
        cartDetail = cartDetail.withNewProducts(productId, amount);
    }

    private void ensureHasState(CartState stateToCheck) {
        if (stateToCheck == state)
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
}
