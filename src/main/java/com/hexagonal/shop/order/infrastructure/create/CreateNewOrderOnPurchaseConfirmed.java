package com.hexagonal.shop.order.infrastructure.create;

import com.hexagonal.shop.shared.domain.bus.event.DomainEventSubscriber;
import com.hexagonal.shop.shared.domain.cart.PurchaseConfirmedDomainEvent;
import com.hexagonal.shop.order.application.create.OrderCreator;
import org.springframework.context.event.EventListener;

@DomainEventSubscriber({PurchaseConfirmedDomainEvent.class})
public class CreateNewOrderOnPurchaseConfirmed {

    private final OrderCreator creator;

    public CreateNewOrderOnPurchaseConfirmed(OrderCreator creator) {
        this.creator = creator;
    }

    @EventListener
    public void on(PurchaseConfirmedDomainEvent event) {
        creator.create(event.getAggregateId(), event.getQuantityPerProduct(), event.getAddress(), event.getDiscountCode(), event.getEmail());
    }
}
