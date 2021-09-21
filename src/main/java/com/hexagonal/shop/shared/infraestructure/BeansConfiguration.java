package com.hexagonal.shop.shared.infraestructure;

import com.hexagonal.shop.cart.application.addtocart.AddProductToCartUseCase;
import com.hexagonal.shop.cart.domain.CartRepository;
import com.hexagonal.shop.cart.domain.ProductRepository;
import com.hexagonal.shop.cart.infrastructure.persistence.CachedCartRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    public AddProductToCartUseCase addProductToCartUseCase(@Qualifier("cachedCartRepository") CartRepository cartRepository,
                                                           ProductRepository productRepository) {
        return new AddProductToCartUseCase(cartRepository, productRepository);
    }

    @Bean
    public CachedCartRepository cachedCartRepository(@Qualifier("jpaCartRepository") CartRepository cartRepository) {
        return new CachedCartRepository(cartRepository);
    }
}
