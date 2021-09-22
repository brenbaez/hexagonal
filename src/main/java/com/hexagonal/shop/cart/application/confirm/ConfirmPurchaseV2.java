package com.hexagonal.shop.cart.application.confirm;

import com.hexagonal.shop.cart.domain.Cart;
import com.hexagonal.shop.cart.domain.CartNotExist;
import com.hexagonal.shop.cart.domain.CartRepository;
import com.hexagonal.shop.order.domain.Order;
import com.hexagonal.shop.order.domain.OrderRepository;
import com.hexagonal.shop.order.domain.ProductRepository;
import com.hexagonal.shop.shared.domain.EmailNotifier;
import com.hexagonal.shop.shared.domain.product.Product;
import com.hexagonal.shop.shared.domain.valueobject.Address;
import com.hexagonal.shop.shared.domain.valueobject.CartId;
import com.hexagonal.shop.shared.domain.valueobject.DiscountCode;
import com.hexagonal.shop.shared.domain.valueobject.Email;
import com.hexagonal.shop.shared.domain.valueobject.ProductId;

import java.util.List;
import java.util.Set;

public class ConfirmPurchaseV2 {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final EmailNotifier emailNotifier;

    public ConfirmPurchaseV2(CartRepository cartRepository,
                             OrderRepository orderRepository,
                             ProductRepository productRepository,
                             EmailNotifier emailNotifier) {

        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.emailNotifier = emailNotifier;
    }

    public void confirm(CartId cartId, DiscountCode discountCode, Email email, Address address) {
        Cart cart = getCart(cartId);
        cart.confirm(discountCode, email, address);
        cartRepository.save(cart);

        List<Product> products = get(cart.getDetail().getProducts().keySet());
        Order order = new Order(products, cart.getDetail().getProducts(), address, discountCode, email);
        orderRepository.save(order);

        emailNotifier.sendNotification(email);
    }

    private Cart getCart(CartId cartId) {
        return cartRepository.get(cartId).orElseThrow(() -> new CartNotExist(cartId));
    }

    private List<Product> get(Set<ProductId> productIds) {
        return productRepository.getAll(productIds);
    }
}
