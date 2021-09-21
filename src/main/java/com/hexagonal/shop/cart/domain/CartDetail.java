package com.hexagonal.shop.cart.domain;

import com.hexagonal.shop.shared.domain.valueobject.ProductId;

import java.util.HashMap;
import java.util.Map;

public class CartDetail {

    private final Map<ProductId, ProductQuantity> products;

    public CartDetail(Map<ProductId, ProductQuantity> newProducts) {
        products = newProducts;
    }

    public Integer total() {
        return products.values()
                .stream()
                .map(ProductQuantity::value)
                .reduce(Integer::sum)
                .orElse(0);
    }

    public Map<ProductId, ProductQuantity> getProducts() {
        return products;
    }

    public CartDetail withNewProducts(ProductId productId, ProductQuantity amount) {
        HashMap<ProductId, ProductQuantity> newProducts = new HashMap<ProductId, ProductQuantity>(products);

        newProducts.put(productId, amount);

        return new CartDetail(newProducts);
    }
}
