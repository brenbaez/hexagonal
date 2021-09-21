package com.hexagonal.shop.cart.infrastructure.persistence;

import com.hexagonal.shop.cart.infrastructure.persistence.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HibernateCartRepository extends JpaRepository<CartEntity, String> {
}
