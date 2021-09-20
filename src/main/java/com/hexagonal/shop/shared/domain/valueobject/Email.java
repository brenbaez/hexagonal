package com.hexagonal.shop.shared.domain.valueobject;

public class Email extends StringValueObject {
    private static final String REGULAR_EXPRESSION_EMAIL = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";

    public Email(String value) {
        super(value);
    }

    @Override
    protected void ensureValid(String value) {
        if (!value.matches(REGULAR_EXPRESSION_EMAIL))
            throw new IllegalArgumentException("Email is not valid");
    }
}
