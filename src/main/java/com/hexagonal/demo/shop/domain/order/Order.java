package com.hexagonal.demo.shop.domain.order;

import com.hexagonal.demo.shared.domain.AggregateRoot;
import com.hexagonal.demo.shop.domain.cart.AmountProducts;
import com.hexagonal.demo.shop.domain.cart.valueobject.Address;
import com.hexagonal.demo.shop.domain.cart.valueobject.DiscountCode;
import com.hexagonal.demo.shop.domain.cart.valueobject.Email;
import com.hexagonal.demo.shop.domain.cart.valueobject.ProductId;
import org.aspectj.weaver.ast.Or;

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
