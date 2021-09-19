package com.hexagonal.demo.shop.application.order.create;

import com.hexagonal.demo.shop.domain.cart.AmountProducts;
import com.hexagonal.demo.shop.domain.cart.valueobject.Address;
import com.hexagonal.demo.shop.domain.cart.valueobject.DiscountCode;
import com.hexagonal.demo.shop.domain.cart.valueobject.Email;
import com.hexagonal.demo.shop.domain.cart.valueobject.ProductId;
import com.hexagonal.demo.shop.domain.order.Order;
import com.hexagonal.demo.shop.domain.order.OrderRepository;
import com.hexagonal.demo.shop.domain.order.Product;
import com.hexagonal.demo.shop.domain.order.ProductRepository;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrderCreator {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderCreator(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public void create(String aggregateId, Map<ProductId, AmountProducts> fullDetail, Address address,
                       DiscountCode discountCode, Email email) {
        List<Product> products = get(fullDetail.keySet());
        Order order = new Order(products, fullDetail, aggregateId, address, discountCode, email);
        orderRepository.save(order);
    }

    private List<Product> get(Set<ProductId> productIds) {
        return productRepository.getAll(productIds);
    }
}
