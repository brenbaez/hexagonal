package com.hexagonal.shop.shared.domain.exception;

import com.hexagonal.shop.shared.domain.DomainError;

public class InvalidIdentifier extends DomainError {
    public InvalidIdentifier(String value) {
        super("invalid_identifier", String.format("Invalid Id format: <%s>", value));
    }
}
