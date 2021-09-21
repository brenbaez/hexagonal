package com.hexagonal.shop.shared.infraestructure.product.persistence;

import com.hexagonal.shop.shared.domain.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductEntityRepository extends JpaRepository<ProductEntity, String> {
}
