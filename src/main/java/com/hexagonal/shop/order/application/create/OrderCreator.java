package com.hexagonal.shop.order.application.create;

import com.hexagonal.shop.cart.domain.ProductQuantity;
import com.hexagonal.shop.order.domain.Order;
import com.hexagonal.shop.order.domain.OrderRepository;
import com.hexagonal.shop.order.domain.ProductRepository;
import com.hexagonal.shop.shared.domain.product.Product;
import com.hexagonal.shop.shared.domain.valueobject.Address;
import com.hexagonal.shop.shared.domain.valueobject.DiscountCode;
import com.hexagonal.shop.shared.domain.valueobject.Email;
import com.hexagonal.shop.shared.domain.valueobject.ProductId;

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

    public Order create(Map<ProductId, ProductQuantity> quantityPerProduct, Address address,
                        DiscountCode discountCode, Email email) {
        List<Product> products = get(quantityPerProduct.keySet());
        Order order = new Order(products, quantityPerProduct, address, discountCode, email);
        orderRepository.save(order);
        return order;
    }

    private List<Product> get(Set<ProductId> productIds) {
        return productRepository.getAll(productIds);
    }
}
