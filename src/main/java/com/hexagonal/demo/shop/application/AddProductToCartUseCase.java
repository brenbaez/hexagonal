package com.hexagonal.demo.shop.application;

import com.hexagonal.demo.shop.domain.AmountProducts;
import com.hexagonal.demo.shop.domain.Cart;
import com.hexagonal.demo.shop.domain.CartId;
import com.hexagonal.demo.shop.domain.CartRepository;
import com.hexagonal.demo.shop.domain.ProductId;
import com.hexagonal.demo.shop.domain.ProductRepository;

final class AddProductToCartUseCase {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public AddProductToCartUseCase(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public void addProductToCart(CartId cartId, ProductId productId, AmountProducts amount) {
        Cart cart = getCart(cartId);
        validateProductExistence(productId);
        cart.add(productId, amount);

        cartRepository.save(cart);
    }

    private void validateProductExistence(ProductId productId) {
        productRepository.get(productId).orElseThrow(() -> new RuntimeException("Product does not exist"));
    }

    private Cart getCart(CartId cartId) {
        return cartRepository.get(cartId)
                .orElse(new Cart(cartId));
    }

}
