package com.hexagonal.shop.order.domain;

import com.hexagonal.shop.cart.domain.ProductQuantity;
import com.hexagonal.shop.shared.domain.AggregateRoot;
import com.hexagonal.shop.shared.domain.product.Product;
import com.hexagonal.shop.shared.domain.valueobject.Address;
import com.hexagonal.shop.shared.domain.valueobject.DiscountCode;
import com.hexagonal.shop.shared.domain.valueobject.Email;
import com.hexagonal.shop.shared.domain.valueobject.ProductId;

import java.util.List;
import java.util.Map;

public class Order extends AggregateRoot {
    private Address address;
    private DiscountCode discountCode;
    private Email email;
    private OrderLines orderLines;

    public Order(List<Product> products, Map<ProductId, ProductQuantity> quantityPerProduct, Address address,
                 DiscountCode discountCode, Email email) {
        super();
        this.address = address;
        this.discountCode = discountCode;
        this.email = email;
        createOrderLines(products, quantityPerProduct);
    }

    public Order(Address address, DiscountCode discountCode, Email email, OrderLines orderLinesDetails) {
        this.address = address;
        this.discountCode = discountCode;
        this.email = email;
        this.orderLines = orderLinesDetails;
    }

    private void createOrderLines(List<Product> products, Map<ProductId, ProductQuantity> quantityPerProduct) {
        this.orderLines = new OrderLines();
        this.orderLines.create(products, quantityPerProduct);
    }

    public Integer details() {
        return orderLines.getTotalPrice();
    }
}
