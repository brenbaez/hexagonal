package com.hexagonal.shop.shared.domain.valueobject;

import com.hexagonal.shop.shared.domain.DomainError;

public class InvalidEmail extends DomainError {
    public InvalidEmail(String value) {
        super("invalid_email", String.format("<%s> is not a valid email", value));
    }
}
