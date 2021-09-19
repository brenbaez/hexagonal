package com.hexagonal.shop.cart.application.create;

import com.hexagonal.shop.cart.domain.AmountProducts;
import com.hexagonal.shop.cart.domain.Cart;
import com.hexagonal.shop.cart.domain.CartRepository;
import com.hexagonal.shop.cart.domain.ProductRepository;
import com.hexagonal.shop.cart.domain.valueobject.CartId;
import com.hexagonal.shop.cart.domain.valueobject.ProductId;

public final class AddProductToCartUseCase {

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
