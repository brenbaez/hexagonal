package com.hexagonal.shop.cart.domain;

import com.hexagonal.shop.order.domain.valueobject.Price;
import com.hexagonal.shop.order.domain.valueobject.ProductName;
import com.hexagonal.shop.shared.domain.product.Product;

public class ProductMother {

    public static Product empty() {
        return new Product(ProductIdMother.random(), new ProductName("product name"), new Price(5));
    }
}
