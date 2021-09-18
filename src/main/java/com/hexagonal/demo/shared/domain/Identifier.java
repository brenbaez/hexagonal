package com.hexagonal.demo.shared.domain;

import java.util.UUID;

public class Identifier {

    private final String value;

    public Identifier(String value) {
        ensureValidUuid(value);
        this.value = value;
    }

    public String value() {
        return value;
    }

    private void ensureValidUuid(String value) {
        UUID.fromString(value);
    }
}
