package com.hexagonal.shop.shared.domain.product;

import com.hexagonal.shop.cart.domain.valueobject.ProductId;
import com.hexagonal.shop.order.domain.valueobject.Price;
import com.hexagonal.shop.order.domain.valueobject.ProductName;
import lombok.Getter;

@Getter
public class Product {

    private final ProductId productId;
    private final ProductName productName;
    private final Price price;

    public Product(ProductId productId, ProductName productName, Price price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    public Product getDetail(){
        return new Product(this.productId, this.productName, this.price);
    }
}
