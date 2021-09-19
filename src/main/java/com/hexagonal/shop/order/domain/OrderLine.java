package com.hexagonal.shop.order.domain;

import com.hexagonal.shop.cart.domain.AmountProducts;
import com.hexagonal.shop.order.domain.valueobject.Price;
import com.hexagonal.shop.order.domain.valueobject.ProductName;
import com.hexagonal.shop.shared.domain.product.Product;

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
