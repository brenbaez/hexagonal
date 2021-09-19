package com.hexagonal.demo.shop.domain.cart;

import com.hexagonal.demo.shop.domain.cart.Product;
import com.hexagonal.demo.shop.domain.cart.valueobject.ProductId;

import java.util.Optional;

public interface ProductRepository {
    Optional<Product> get(ProductId productId);
}
