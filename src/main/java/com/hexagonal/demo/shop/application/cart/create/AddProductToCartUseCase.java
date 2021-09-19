package com.hexagonal.demo.shop.application.cart.create;

import com.hexagonal.demo.shop.domain.cart.AmountProducts;
import com.hexagonal.demo.shop.domain.cart.Cart;
import com.hexagonal.demo.shop.domain.cart.valueobject.CartId;
import com.hexagonal.demo.shop.domain.cart.CartRepository;
import com.hexagonal.demo.shop.domain.cart.valueobject.ProductId;
import com.hexagonal.demo.shop.domain.cart.ProductRepository;

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
