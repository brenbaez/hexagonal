package com.hexagonal.demo.shop.domain.order;

import com.hexagonal.demo.shop.domain.cart.AmountProducts;
import com.hexagonal.demo.shop.domain.order.valueobject.Price;
import com.hexagonal.demo.shop.domain.order.valueobject.ProductName;

public class OrderLine {
    private ProductName productName;
    private Price individualPrice;
    private AmountProducts amountProducts;

    public OrderLine(Product detail, AmountProducts amountProducts) {
        this.productName = detail.getProductName();
        this.individualPrice = detail.getPrice();
        this.amountProducts = amountProducts;
    }

    public Integer getPrice() {
        return individualPrice.value();
    }
}
