package com.hexagonal.demo.shop.domain.order;

import com.hexagonal.demo.shop.domain.cart.AmountProducts;
import com.hexagonal.demo.shop.domain.cart.valueobject.ProductId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderLines {

    private List<OrderLine> orderLines;

    public OrderLines() {
        this.orderLines = new ArrayList<>();
    }

    public void create(List<Product> products, Map<ProductId, AmountProducts> fullDetail) {
        orderLines = products
                .stream()
                .map(product -> new OrderLine(product, fullDetail.get(product.getProductId())))
                .collect(Collectors.toList());
    }

    public Integer getTotalPrice(){
        return orderLines.stream().map(OrderLine::getPrice).reduce(Integer::sum)
                .orElse(0);
    }
}
