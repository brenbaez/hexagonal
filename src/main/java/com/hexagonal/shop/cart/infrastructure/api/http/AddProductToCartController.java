package com.hexagonal.shop.cart.infrastructure.api.http;

import com.hexagonal.shop.cart.application.create.AddProductToCartUseCase;
import com.hexagonal.shop.cart.domain.ProductQuantity;
import com.hexagonal.shop.shared.domain.valueobject.CartId;
import com.hexagonal.shop.shared.domain.valueobject.ProductId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddProductToCartController {

    private final AddProductToCartUseCase addProductToCartUseCase;

    public AddProductToCartController(AddProductToCartUseCase addProductToCartUseCase) {
        this.addProductToCartUseCase = addProductToCartUseCase;
    }

    @PostMapping("/cart")
    public ResponseEntity<Map<String, Object>> addProductToCart() {
        addProductToCartUseCase.addProductToCart(
                new CartId("cartId"),
                new ProductId("productId"),
                new ProductQuantity(1));
        return ResponseEntity.ok().build();
    }
}
