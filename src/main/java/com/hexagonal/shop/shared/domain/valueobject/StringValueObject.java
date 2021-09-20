package com.hexagonal.shop.shared.domain.valueobject;

import java.util.Objects;

public abstract class StringValueObject {

    private String value;

    public StringValueObject(String value) {
        ensureValid(value);
        this.value = value;
    }

    public String value() {
        return value;
    }

    protected void ensureValid(String value) {
    }

    @Override
    public String toString() {
        return this.value();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StringValueObject)) {
            return false;
        }
        StringValueObject that = (StringValueObject) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}