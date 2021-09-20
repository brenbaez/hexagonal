package com.hexagonal.shop.order.domain;

import com.hexagonal.shop.cart.domain.QuantityProduct;
import com.hexagonal.shop.order.domain.valueobject.Price;
import com.hexagonal.shop.order.domain.valueobject.ProductName;
import com.hexagonal.shop.shared.domain.product.Product;

public class OrderLine {
    private ProductName productName;
    private Price individualPrice;
    private QuantityProduct quantityProduct;

    public OrderLine(Product detail, QuantityProduct quantityProduct) {
        this.productName = detail.getProductName();
        this.individualPrice = detail.getPrice();
        this.quantityProduct = quantityProduct;
    }

    public Integer getPrice() {
        return individualPrice.value();
    }
}
