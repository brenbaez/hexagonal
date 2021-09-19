package com.hexagonal.shop.shared.infraestructure.product;

import com.hexagonal.shop.shared.domain.valueobject.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductEntityRepository extends JpaRepository<ProductEntity, String> {
}
