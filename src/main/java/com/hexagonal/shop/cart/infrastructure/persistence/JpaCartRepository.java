package com.hexagonal.shop.cart.infrastructure.persistence;

import com.hexagonal.shop.cart.domain.QuantityProduct;
import com.hexagonal.shop.cart.domain.Cart;
import com.hexagonal.shop.cart.domain.CartRepository;
import com.hexagonal.shop.cart.infrastructure.persistence.entity.CartEntity;
import com.hexagonal.shop.shared.domain.valueobject.CartId;
import com.hexagonal.shop.shared.domain.valueobject.ProductId;
import com.hexagonal.shop.shared.infraestructure.product.persistence.JpaProductEntityRepository;
import com.hexagonal.shop.shared.domain.product.ProductEntity;
import com.hexagonal.shop.shared.infraestructure.product.persistence.ProductQuantityEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository("jpaCartRepository")
public class JpaCartRepository implements CartRepository {

    private final HibernateCartRepository hibernateCartRepository;
    private final JpaProductEntityRepository jpaProductEntityRepository;

    public JpaCartRepository(HibernateCartRepository hibernateCartRepository,
                             JpaProductEntityRepository jpaProductEntityRepository) {
        this.hibernateCartRepository = hibernateCartRepository;
        this.jpaProductEntityRepository = jpaProductEntityRepository;
    }

    @Override
    public Optional<Cart> get(CartId cartId) {
        Optional<CartEntity> cartEntityOp = hibernateCartRepository.findById(cartId.value());
        if (cartEntityOp.isEmpty())
            return Optional.empty();
        CartEntity cartEntity = cartEntityOp.get();
        Cart cart = new Cart(new CartId(cartEntity.getId()));
        List<ProductQuantityEntity> products = cartEntity.getProducts();
        products.forEach(product -> cart.add(new ProductId(product.getId()), new QuantityProduct(product.getQuantity())));
        return Optional.of(cart);
    }

    @Override
    public void save(Cart cart) {
        CartEntity cartEntity = hibernateCartRepository.findById(cart.getId().value()).orElse(new CartEntity(cart.getId().value()));
        addProducts(cartEntity, cart);
        cartEntity.setClosed(cart.isConfirmed());
        hibernateCartRepository.save(cartEntity);
    }

    private void addProducts(CartEntity cartEntity, Cart cart) {
        Map<String, Integer> quantityPerProductId = cart.getDetail().getProducts().entrySet()
                .stream()
                .collect(Collectors.toMap(key -> key.getKey().value(), value -> value.getValue().value()));
        Map<String, ProductEntity> productEntities = jpaProductEntityRepository.findAllById(quantityPerProductId.keySet())
                .stream()
                .collect(Collectors.toMap(ProductEntity::getProductId, Function.identity()));
        cartEntity.setProducts(new ArrayList<>());
        hibernateCartRepository.save(cartEntity);
        List<ProductQuantityEntity> productQuantityEntityList = quantityPerProductId.entrySet()
                .stream()
                .map(detail -> new ProductQuantityEntity(productEntities.get(detail.getKey()), detail.getValue()))
                .collect(Collectors.toList());
        cartEntity.setProducts(productQuantityEntityList);
    }
}
