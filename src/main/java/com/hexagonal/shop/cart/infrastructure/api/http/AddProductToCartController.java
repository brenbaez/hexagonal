package com.hexagonal.shop.cart.infrastructure.api.http;

import com.hexagonal.shop.cart.application.addtocart.AddProductToCartUseCase;
import com.hexagonal.shop.cart.domain.ProductQuantity;
import com.hexagonal.shop.shared.domain.valueobject.CartId;
import com.hexagonal.shop.shared.domain.valueobject.ProductId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class AddProductToCartController {

    private final AddProductToCartUseCase addProductToCartUseCase;

    public AddProductToCartController(AddProductToCartUseCase addProductToCartUseCase) {
        this.addProductToCartUseCase = addProductToCartUseCase;
    }

    @PostMapping("/cart")
    public ResponseEntity<Map<String, Object>> addProductToCart(@RequestBody @Valid AddProductToCartRequest request) {

        addProductToCartUseCase.addProductToCart(
                new CartId(request.getCartId()),
                new ProductId(request.getProductId()),
                new ProductQuantity(request.getQuantity()));

        return ResponseEntity.ok().build();
    }

}
