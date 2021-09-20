package com.hexagonal.shop.shared.domain.product;

import com.hexagonal.shop.shared.domain.valueobject.ProductId;

import java.util.List;
import java.util.Set;

public interface ProductRepository {
    List<Product> getAll(Set<ProductId> productIds);
}
