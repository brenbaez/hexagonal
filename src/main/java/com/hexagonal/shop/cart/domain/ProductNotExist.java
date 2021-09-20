package com.hexagonal.shop.cart.domain;

import com.hexagonal.shop.cart.domain.valueobject.ProductId;
import com.hexagonal.shop.shared.domain.DomainError;

public final class ProductNotExist extends DomainError {

    public ProductNotExist(ProductId id) {
        super("product_not_exist", String.format("The product <%s> doesn't exist", id.value()));
    }
}