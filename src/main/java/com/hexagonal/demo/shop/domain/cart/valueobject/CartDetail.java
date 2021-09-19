package com.hexagonal.demo.shop.domain.cart.valueobject;

import com.hexagonal.demo.shop.domain.cart.AmountProducts;

import java.util.HashMap;
import java.util.Map;

public class CartDetail {

    private final Map<ProductId, AmountProducts> products;

    public CartDetail(Map<ProductId, AmountProducts> newProducts) {
        products = newProducts;
    }

    public Integer total() {
        return products.values()
                .stream()
                .map(AmountProducts::value)
                .reduce(Integer::sum)
                .orElse(0);
    }

    public Map<ProductId, AmountProducts> getProducts() {
        return products;
    }

    public CartDetail withNewProducts(ProductId productId, AmountProducts amount) {
        HashMap<ProductId, AmountProducts> newProducts = new HashMap<ProductId, AmountProducts>(products);

        newProducts.put(productId, amount);

        return new CartDetail(newProducts);
    }
}
