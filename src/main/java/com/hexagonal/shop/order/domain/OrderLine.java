package com.hexagonal.shop.order.domain;

import com.hexagonal.shop.cart.domain.ProductQuantity;
import com.hexagonal.shop.order.domain.valueobject.Price;
import com.hexagonal.shop.order.domain.valueobject.ProductName;
import com.hexagonal.shop.shared.domain.product.Product;

public class OrderLine {
    private ProductName productName;
    private Price individualPrice;
    private ProductQuantity productQuantity;

    public OrderLine(Product detail, ProductQuantity productQuantity) {
        this.productName = detail.getProductName();
        this.individualPrice = detail.getPrice();
        this.productQuantity = productQuantity;
    }

    public Integer getPrice() {
        return individualPrice.value();
    }
}
