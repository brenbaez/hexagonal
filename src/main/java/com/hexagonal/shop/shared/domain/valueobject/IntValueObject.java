package com.hexagonal.shop.shared.domain.valueobject;

public class IntValueObject {
    private Integer value;

    public IntValueObject(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return value;
    }

}