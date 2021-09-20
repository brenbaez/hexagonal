package com.hexagonal.shop.cart.domain;

import com.hexagonal.shop.shared.domain.valueobject.ProductId;
import com.hexagonal.shop.shared.domain.product.Product;

import java.util.Optional;

public interface ProductRepository {
    Optional<Product> get(ProductId productId);
}
