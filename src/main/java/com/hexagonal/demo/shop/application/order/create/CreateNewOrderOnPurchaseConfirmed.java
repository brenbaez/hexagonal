package com.hexagonal.demo.shop.application.order.create;

import com.hexagonal.demo.shared.domain.bus.event.DomainEventSubscriber;
import com.hexagonal.demo.shared.domain.cart.PurchaseConfirmedDomainEvent;
import org.springframework.context.event.EventListener;

@DomainEventSubscriber({PurchaseConfirmedDomainEvent.class})
public class CreateNewOrderOnPurchaseConfirmed {

    private final OrderCreator creator;

    public CreateNewOrderOnPurchaseConfirmed(OrderCreator creator) {
        this.creator = creator;
    }

    @EventListener
    public void on(PurchaseConfirmedDomainEvent event){
        creator.create(event.getAggregateId(), event.getFullDetail(), event.getAddress(), event.getDiscountCode(), event.getEmail());
    }
}
