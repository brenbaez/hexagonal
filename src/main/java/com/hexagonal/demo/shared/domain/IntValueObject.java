package com.hexagonal.demo.shared.domain;

public class IntValueObject {
    private Integer value;

    public IntValueObject(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return value;
    }

}