package com.hexagonal.shop.shared.infraestructure.product;

import com.hexagonal.shop.cart.domain.ProductRepository;
import com.hexagonal.shop.shared.domain.valueobject.ProductId;
import com.hexagonal.shop.shared.domain.product.Product;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaProductRepository implements ProductRepository {
    @Override
    public Optional<Product> get(ProductId productId) {
        return Optional.empty();
    }
}
