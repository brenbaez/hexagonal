package com.hexagonal.demo.shop.domain;

import java.util.Optional;

public interface ProductRepository {
    Optional<Product> get(ProductId productId);
}
