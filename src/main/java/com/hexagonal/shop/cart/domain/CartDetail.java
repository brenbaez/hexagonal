package com.hexagonal.shop.cart.domain;

import com.hexagonal.shop.shared.domain.valueobject.ProductId;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CartDetail {

    private final Map<ProductId, ProductQuantity> products;

    public CartDetail(Map<ProductId, ProductQuantity> newProducts) {
        products = newProducts;
    }

    public Integer totalProductQuantity() {
        return products.values()
                .stream()
                .map(ProductQuantity::value)
                .reduce(Integer::sum)
                .orElse(0);
    }

    public Map<ProductId, ProductQuantity> getProducts() {
        return Collections.unmodifiableMap(products);
    }

    public CartDetail withNewProducts(ProductId productId, ProductQuantity quantity) {
        HashMap<ProductId, ProductQuantity> newProducts = new HashMap<ProductId, ProductQuantity>(products);
        newProducts.compute(productId, (prodId, pQty) -> pQty == null ? quantity : pQty.plus(quantity));
        return new CartDetail(newProducts);
    }
}
