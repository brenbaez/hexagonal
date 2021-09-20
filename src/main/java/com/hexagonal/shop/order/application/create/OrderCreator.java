package com.hexagonal.shop.order.application.create;

import com.hexagonal.shop.cart.domain.QuantityProduct;
import com.hexagonal.shop.shared.domain.valueobject.Address;
import com.hexagonal.shop.shared.domain.valueobject.DiscountCode;
import com.hexagonal.shop.shared.domain.valueobject.Email;
import com.hexagonal.shop.shared.domain.valueobject.ProductId;
import com.hexagonal.shop.order.domain.Order;
import com.hexagonal.shop.order.domain.OrderRepository;
import com.hexagonal.shop.shared.domain.product.Product;
import com.hexagonal.shop.shared.domain.product.ProductRepository;

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

    public void create(String aggregateId, Map<ProductId, QuantityProduct> fullDetail, Address address,
                       DiscountCode discountCode, Email email) {
        List<Product> products = get(fullDetail.keySet());
        Order order = new Order(products, fullDetail, aggregateId, address, discountCode, email);
        orderRepository.save(order);
    }

    private List<Product> get(Set<ProductId> productIds) {
        return productRepository.getAll(productIds);
    }
}
