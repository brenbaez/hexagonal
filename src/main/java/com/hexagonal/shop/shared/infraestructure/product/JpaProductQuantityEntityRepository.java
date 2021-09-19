package com.hexagonal.shop.shared.infraestructure.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductQuantityEntityRepository extends JpaRepository<ProductQuantityEntity, String> {
}
