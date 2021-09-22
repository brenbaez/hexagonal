package com.hexagonal.shop.shared.domain.exception;

import com.hexagonal.shop.shared.domain.DomainError;

public class ProductQuantityNegative extends DomainError {

    public ProductQuantityNegative(Integer quantity) {
        super("product_not_exist",
                String.format("The quantity of products cannot be negative or zero: <%s>", quantity));
    }
}
