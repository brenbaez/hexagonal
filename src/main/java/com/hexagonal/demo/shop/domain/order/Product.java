package com.hexagonal.demo.shop.domain.order;

import com.hexagonal.demo.shop.domain.cart.valueobject.ProductId;
import com.hexagonal.demo.shop.domain.order.valueobject.Price;
import com.hexagonal.demo.shop.domain.order.valueobject.ProductName;
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
