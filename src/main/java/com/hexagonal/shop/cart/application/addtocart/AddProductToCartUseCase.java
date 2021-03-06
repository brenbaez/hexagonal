package com.hexagonal.shop.cart.application.addtocart;

import com.hexagonal.shop.cart.domain.ProductQuantity;
import com.hexagonal.shop.cart.domain.Cart;
import com.hexagonal.shop.cart.domain.CartRepository;
import com.hexagonal.shop.shared.domain.exception.ProductNotExist;
import com.hexagonal.shop.cart.domain.ProductRepository;
import com.hexagonal.shop.shared.domain.valueobject.CartId;
import com.hexagonal.shop.shared.domain.valueobject.ProductId;

public final class AddProductToCartUseCase {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public AddProductToCartUseCase(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public void addProductToCart(CartId cartId, ProductId productId, ProductQuantity quantity) {
        Cart cart = getCart(cartId);
        validateProductExistence(productId);
        cart.add(productId, quantity);

        cartRepository.save(cart);
    }

    private void validateProductExistence(ProductId productId) {
        productRepository.get(productId).orElseThrow(() -> new ProductNotExist(productId));
    }

    private Cart getCart(CartId cartId) {
        return cartRepository.get(cartId)
                .orElse(new Cart(cartId));
    }

}
