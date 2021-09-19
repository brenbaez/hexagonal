package com.hexagonal.demo.shared.domain.cart;

import com.hexagonal.demo.shared.domain.bus.event.DomainEvent;
import com.hexagonal.demo.shop.domain.cart.AmountProducts;
import com.hexagonal.demo.shop.domain.cart.valueobject.Address;
import com.hexagonal.demo.shop.domain.cart.valueobject.CartId;
import com.hexagonal.demo.shop.domain.cart.valueobject.DiscountCode;
import com.hexagonal.demo.shop.domain.cart.valueobject.Email;
import com.hexagonal.demo.shop.domain.cart.valueobject.ProductId;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Getter //to simplify
public class PurchaseConfirmedDomainEvent extends DomainEvent {
    private final Map<ProductId, AmountProducts> fullDetail;
    private final DiscountCode discountCode;
    private final Email email;
    private final Address address;

    public PurchaseConfirmedDomainEvent(CartId id,
                                        Map<ProductId, AmountProducts> fullDetail,
                                        DiscountCode discountCode,
                                        Email email, Address address) {
        super(id.value());
        this.fullDetail = fullDetail;
        this.discountCode = discountCode;
        this.email = email;
        this.address = address;
    }

    public PurchaseConfirmedDomainEvent(String aggregateId, String eventId, String occurredOn, Map<ProductId, AmountProducts> fullDetail, DiscountCode discountCode, Email email, Address address) {
        super(aggregateId, eventId, occurredOn);
        this.fullDetail = fullDetail;
        this.discountCode = discountCode;
        this.email = email;
        this.address = address;
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        Map<String, Integer> details = fullDetail.entrySet()
                .stream()
                .collect(Collectors.toMap(key-> key.getKey().value(), value -> value.getValue().value()));
        return new HashMap<String, Serializable>() {{
            put("fullDetail", (Serializable) details);
            put("discountCode", discountCode.value());
            put("email", email.value());
            put("address", address.value());
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId, Map<String, Serializable> body, String eventId, String occurredOn) {
        return new PurchaseConfirmedDomainEvent(
                aggregateId,
                eventId,
                occurredOn,
                ( Map<ProductId, AmountProducts>)body.get("fullDetail"),
                (DiscountCode)body.get("discountCode"),
                (Email) body.get("email"),
                (Address)body.get("address")
        );
    }
}
