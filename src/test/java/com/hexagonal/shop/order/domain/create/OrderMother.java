package com.hexagonal.shop.order.domain.create;

import com.hexagonal.shop.cart.domain.ProductQuantity;
import com.hexagonal.shop.order.domain.Order;
import com.hexagonal.shop.shared.domain.product.Product;
import com.hexagonal.shop.shared.domain.valueobject.Address;
import com.hexagonal.shop.shared.domain.valueobject.DiscountCode;
import com.hexagonal.shop.shared.domain.valueobject.Email;
import com.hexagonal.shop.shared.domain.valueobject.ProductId;

import java.util.List;
import java.util.Map;

public class OrderMother {
    public static Order withData(List<Product> products, Map<ProductId, ProductQuantity> quantityPerProduct,
                                 DiscountCode discountCode, Address address, Email email) {
        return new Order(products, quantityPerProduct, address, discountCode, email);
    }
}
