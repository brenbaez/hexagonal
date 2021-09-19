package com.hexagonal.shop.order.domain;

public interface OrderRepository {
    void save(Order order);
}
