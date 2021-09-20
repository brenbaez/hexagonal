package com.hexagonal.shop.cart.domain;

import com.hexagonal.shop.shared.domain.valueobject.ProductId;

import java.util.HashMap;
import java.util.Map;

public class CartDetail {

    private final Map<ProductId, QuantityProduct> products;

    public CartDetail(Map<ProductId, QuantityProduct> newProducts) {
        products = newProducts;
    }

    public Integer total() {
        return products.values()
                .stream()
                .map(QuantityProduct::value)
                .reduce(Integer::sum)
                .orElse(0);
    }

    public Map<ProductId, QuantityProduct> getProducts() {
        return products;
    }

    public CartDetail withNewProducts(ProductId productId, QuantityProduct amount) {
        HashMap<ProductId, QuantityProduct> newProducts = new HashMap<ProductId, QuantityProduct>(products);

        newProducts.put(productId, amount);

        return new CartDetail(newProducts);
    }
}
