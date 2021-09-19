package com.hexagonal.shop.cart.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HibernateCartRepository extends JpaRepository<CartEntity, String> {
}
