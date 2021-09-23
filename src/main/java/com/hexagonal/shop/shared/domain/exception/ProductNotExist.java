package com.hexagonal.shop.shared.domain.exception;

import com.hexagonal.shop.shared.domain.DomainError;
import com.hexagonal.shop.shared.domain.valueobject.ProductId;

public final class ProductNotExist extends DomainError {

    public ProductNotExist(ProductId id) {
        super("product_not_exist", String.format("The product <%s> doesn't exist", id.value()));
    }
}