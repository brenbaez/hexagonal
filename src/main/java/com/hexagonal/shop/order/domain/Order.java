package com.hexagonal.shop.order.domain;

import com.hexagonal.shop.shared.domain.AggregateRoot;
import com.hexagonal.shop.cart.domain.AmountProducts;
import com.hexagonal.shop.cart.domain.valueobject.Address;
import com.hexagonal.shop.cart.domain.valueobject.DiscountCode;
import com.hexagonal.shop.cart.domain.valueobject.Email;
import com.hexagonal.shop.cart.domain.valueobject.ProductId;
import com.hexagonal.shop.shared.domain.product.Product;

import java.util.List;
import java.util.Map;

public class Order extends AggregateRoot {
    private Address address;
    private DiscountCode discountCode;
    private Email email;
    private OrderLines orderLines;

    public Order(List<Product> products, Map<ProductId, AmountProducts> fullDetail, String aggregateId, Address address,
                 DiscountCode discountCode, Email email) {
        super();
        this.address = address;
        this.discountCode = discountCode;
        this.email = email;
        createOrderLines(products, fullDetail);
    }

    private void createOrderLines(List<Product> products, Map<ProductId, AmountProducts> fullDetail) {
        this.orderLines = new OrderLines();
        this.orderLines.create(products, fullDetail);
    }

}
