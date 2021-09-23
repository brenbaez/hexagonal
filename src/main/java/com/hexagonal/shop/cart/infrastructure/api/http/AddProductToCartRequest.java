package com.hexagonal.shop.cart.infrastructure.api.http;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AddProductToCartRequest {
    @NotNull
    private String cartId;
    @NotNull
    private String productId;
    @NotNull
    private Integer quantity;
}