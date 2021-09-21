package com.hexagonal.shop.order.domain;

import com.hexagonal.shop.cart.domain.ProductQuantity;
import com.hexagonal.shop.shared.domain.valueobject.ProductId;
import com.hexagonal.shop.shared.domain.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderLines {

    private List<OrderLine> orderLines;

    public OrderLines() {
        this.orderLines = new ArrayList<>();
    }

    public void create(List<Product> products, Map<ProductId, ProductQuantity> quantityPerProduct) {
        orderLines = products
                .stream()
                .map(product -> new OrderLine(product, quantityPerProduct.get(product.getProductId())))
                .collect(Collectors.toList());
    }

    public Integer getTotalPrice(){
        return orderLines.stream().map(OrderLine::getPrice).reduce(Integer::sum)
                .orElse(0);
    }
}
