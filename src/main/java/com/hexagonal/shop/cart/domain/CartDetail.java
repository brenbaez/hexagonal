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

    public CartDetail withNewProducts(ProductId productId, ProductQuantity quantity) {
        HashMap<ProductId, ProductQuantity> newProducts = new HashMap<ProductId, ProductQuantity>(products);
        if (newProducts.containsKey(productId))
            newProducts.put(productId, new ProductQuantity(newProducts.get(productId).value() + quantity.value()));
        else
            newProducts.put(productId, quantity);
        return new CartDetail(newProducts);
    }
}
