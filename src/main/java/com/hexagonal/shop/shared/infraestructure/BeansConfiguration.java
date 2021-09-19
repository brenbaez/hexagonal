package com.hexagonal.shop.shared.infraestructure;

import com.hexagonal.shop.cart.application.create.AddProductToCartUseCase;
import com.hexagonal.shop.cart.domain.CartRepository;
import com.hexagonal.shop.cart.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    public AddProductToCartUseCase addProductToCartUseCase(@Qualifier("jpaCartRepository") CartRepository cartRepository,
                                                           ProductRepository productRepository){
        return new AddProductToCartUseCase(cartRepository, productRepository);
    }
}
