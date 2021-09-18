package com.hexagonal.demo.shop.domain;

import com.hexagonal.demo.shared.domain.IntValueObject;

import java.util.HashMap;
import java.util.Map;

public class CartDetail {

    private final HashMap<ProductId, AmountProducts> products;

    public CartDetail(HashMap<ProductId, AmountProducts> newProducts) {
        products = newProducts;
    }

    public Integer total() {
        return products.values()
                .stream()
                .map(AmountProducts::value)
                .reduce(Integer::sum)
                .orElse(0);
    }

    public CartDetail withNewProducts(ProductId productId, AmountProducts amount) {
        HashMap<ProductId, AmountProducts> newProducts = new HashMap<ProductId, AmountProducts>(products);

        newProducts.put(productId, amount);

        return new CartDetail(newProducts);
    }
}
