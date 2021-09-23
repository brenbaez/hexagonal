package com.hexagonal.shop.shared.domain.exception;

import com.hexagonal.shop.shared.domain.DomainError;

public class EmptyProductName extends DomainError {

    public EmptyProductName() {
        super("product_name_empty", "Product Name cannot be empty");
    }
}
