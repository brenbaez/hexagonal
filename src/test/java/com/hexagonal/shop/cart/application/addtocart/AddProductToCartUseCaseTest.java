package com.hexagonal.shop.cart.application.addtocart;

import com.hexagonal.shop.cart.domain.CartMother;
import com.hexagonal.shop.cart.domain.CartRepository;
import com.hexagonal.shop.shared.domain.exception.ProductNotExist;
import com.hexagonal.shop.cart.domain.ProductQuantity;
import com.hexagonal.shop.cart.domain.ProductRepository;
import com.hexagonal.shop.shared.domain.ProductIdMother;
import com.hexagonal.shop.shared.domain.ProductMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AddProductToCartUseCaseTest {

    private CartRepository cartRepository;
    private ProductRepository productRepository;
    private AddProductToCartUseCase useCaseSUT;

    @BeforeEach
    void setUp() {
        cartRepository = Mockito.mock(CartRepository.class);
        productRepository = Mockito.mock(ProductRepository.class);
        useCaseSUT = new AddProductToCartUseCase(cartRepository, productRepository);
    }

    @Test
    void shouldAddNewProductToEmptyCart() {
        var cart = CartMother.empty();
        var cartId = cart.getId();
        var product = ProductMother.randomProduct();
        var productId = product.getProductId();
        var quantity = new ProductQuantity(1);

        when(cartRepository.get(cartId)).thenReturn(Optional.of(cart));
        when(productRepository.get(productId)).thenReturn(Optional.of(product));

        useCaseSUT.addProductToCart(cartId, productId, quantity);

        verify(cartRepository, times(1)).save(cart);

        var cartDetail = cart.getDetail();
        assertEquals(quantity.value(), cartDetail.total());
    }

    @Test
    void shouldAddNewProductToExistentCart() {
        var cart = CartMother.withData();
        var cartId = cart.getId();
        var product = ProductMother.randomProduct();
        var productId = product.getProductId();
        var quantity = new ProductQuantity(1);
        var currentTotal = cart.getDetail().total();
        var expectedTotal = currentTotal + quantity.value();

        when(cartRepository.get(cartId)).thenReturn(Optional.of(cart));
        when(productRepository.get(productId)).thenReturn(Optional.of(product));

        useCaseSUT.addProductToCart(cartId, productId, quantity);

        verify(cartRepository, times(1)).save(cart);

        var cartDetail = cart.getDetail();

        assertEquals(expectedTotal, cartDetail.total());
    }

    @Test
    void shouldThrowErrorWhenProductNotExists() {
        var cart = CartMother.empty();
        var cartId = cart.getId();
        var productIdRandom = ProductIdMother.random();

        when(cartRepository.get(cartId)).thenReturn(Optional.of(cart));
        when(productRepository.get(Mockito.any())).thenReturn(Optional.empty());
        Exception exception = assertThrows(ProductNotExist.class, () -> {
            useCaseSUT.addProductToCart(cartId, productIdRandom, new ProductQuantity(1));
        });

        String expectedMessage = "The product <" + productIdRandom.value() + "> doesn't exist";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(cartRepository, never()).save(cart);
    }
}