package com.hexagonal.shop.shared.domain;

import com.hexagonal.shop.order.domain.valueobject.ProductName;
import com.hexagonal.shop.shared.domain.product.Product;
import org.apache.commons.lang3.RandomStringUtils;

public class ProductMother {

    public static Product randomProduct() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;

        return new Product(
                ProductIdMother.random(),
                new ProductName(RandomStringUtils.random(length, useLetters, useNumbers)),
                PriceMother.randomPrice());
    }
}
