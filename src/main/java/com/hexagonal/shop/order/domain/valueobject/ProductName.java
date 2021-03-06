package com.hexagonal.shop.order.domain.valueobject;

import com.hexagonal.shop.shared.domain.exception.EmptyProductName;
import com.hexagonal.shop.shared.domain.valueobject.StringValueObject;

public class ProductName extends StringValueObject {

    public ProductName(String value) {
        super(value);
    }

    @Override
    protected void ensureValid(String value) {
        if (value.isEmpty())
            throw new EmptyProductName();
    }
}
