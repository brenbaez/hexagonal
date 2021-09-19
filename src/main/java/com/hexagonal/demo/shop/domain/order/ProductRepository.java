package com.hexagonal.demo.shop.domain.order;

import com.hexagonal.demo.shop.domain.cart.valueobject.ProductId;

import java.util.List;
import java.util.Set;

public interface ProductRepository {
    List<Product> getAll(Set<ProductId> productIds);
}
