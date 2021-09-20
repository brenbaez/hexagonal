package com.hexagonal.shop.shared.domain.valueobject;

public class DiscountCode extends StringValueObject {
    public DiscountCode(String value) {
        super(value);
    }

    @Override
    protected void ensureValid(String value) {
        if (value.length() != 10)
            throw new IllegalArgumentException("Discount Code not valid");
    }

}
