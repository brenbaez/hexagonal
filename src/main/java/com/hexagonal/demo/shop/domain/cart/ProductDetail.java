package com.hexagonal.demo.shop.domain.cart;

import com.hexagonal.demo.shop.domain.cart.AmountProducts;
import com.hexagonal.demo.shop.domain.cart.valueobject.ProductId;

public class ProductDetail {

    private ProductId productId;
    private AmountProducts amountProducts;

    public ProductDetail(ProductId productId, AmountProducts amountProducts) {
    }
}
