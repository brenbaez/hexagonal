package com.hexagonal.shop.discount.infrastucture;

import com.hexagonal.shop.discount.application.DiscountNextPurchaseUseCase;
import com.hexagonal.shop.shared.domain.bus.event.DomainEventSubscriber;
import com.hexagonal.shop.shared.domain.cart.PurchaseConfirmedDomainEvent;
import org.springframework.context.event.EventListener;

@DomainEventSubscriber({PurchaseConfirmedDomainEvent.class})
public class DiscountNextPurchaseOnPurchaseConfirmed {

    private final DiscountNextPurchaseUseCase discountNextPurchase;

    public DiscountNextPurchaseOnPurchaseConfirmed(DiscountNextPurchaseUseCase discountNextPurchase) {
        this.discountNextPurchase = discountNextPurchase;
    }

    @EventListener
    public void on(PurchaseConfirmedDomainEvent event) {
        discountNextPurchase.discount(event.getDiscountCode());
    }
}
