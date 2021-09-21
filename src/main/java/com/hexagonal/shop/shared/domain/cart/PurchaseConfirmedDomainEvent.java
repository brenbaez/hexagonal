package com.hexagonal.shop.shared.domain.cart;

import com.hexagonal.shop.shared.domain.bus.event.DomainEvent;
import com.hexagonal.shop.cart.domain.ProductQuantity;
import com.hexagonal.shop.shared.domain.valueobject.Address;
import com.hexagonal.shop.shared.domain.valueobject.CartId;
import com.hexagonal.shop.shared.domain.valueobject.DiscountCode;
import com.hexagonal.shop.shared.domain.valueobject.Email;
import com.hexagonal.shop.shared.domain.valueobject.ProductId;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Getter //to simplify
public class PurchaseConfirmedDomainEvent extends DomainEvent {
    private final Map<ProductId, ProductQuantity> quantityPerProduct;
    private final DiscountCode discountCode;
    private final Email email;
    private final Address address;

    public PurchaseConfirmedDomainEvent(CartId id,
                                        Map<ProductId, ProductQuantity> quantityPerProduct,
                                        DiscountCode discountCode,
                                        Email email, Address address) {
        super(id.value());
        this.quantityPerProduct = quantityPerProduct;
        this.discountCode = discountCode;
        this.email = email;
        this.address = address;
    }

    public PurchaseConfirmedDomainEvent(String aggregateId, String eventId, String occurredOn, Map<ProductId, ProductQuantity> quantityPerProduct, DiscountCode discountCode, Email email, Address address) {
        super(aggregateId, eventId, occurredOn);
        this.quantityPerProduct = quantityPerProduct;
        this.discountCode = discountCode;
        this.email = email;
        this.address = address;
    }

    @Override
    public Map<String, Serializable> toPrimitives() {
        Map<String, Integer> quantityPerProductPrimitives = quantityPerProduct.entrySet()
                .stream()
                .collect(Collectors.toMap(key -> key.getKey().value(), value -> value.getValue().value()));

        HashMap<String, Serializable> event = new HashMap<>();
        event.put("quantityPerProduct", (Serializable) quantityPerProductPrimitives);
        event.put("discountCode", discountCode.value());
        event.put("email", email.value());
        event.put("address", address.value());
        return event;
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId, Map<String, Serializable> body, String eventId, String occurredOn) {
        return new PurchaseConfirmedDomainEvent(
                aggregateId,
                eventId,
                occurredOn,
                getQuantityPerProductFromPrimitives((Map<String, Integer>) body.get("quantityPerProduct")),
                new DiscountCode((String) body.get("discountCode")),
                new Email((String) body.get("email")),
                new Address((String) body.get("address"))
        );
    }

    private Map<ProductId, ProductQuantity> getQuantityPerProductFromPrimitives(Map<String, Integer> body) {
        Map<ProductId, ProductQuantity> quantityPerProductFromPrimitives = new HashMap<>();
        body.forEach((id, quantity) -> quantityPerProductFromPrimitives.put(new ProductId(id), new ProductQuantity(quantity)));

        return quantityPerProductFromPrimitives;
    }
}
