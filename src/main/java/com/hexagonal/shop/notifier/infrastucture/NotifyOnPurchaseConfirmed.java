package com.hexagonal.shop.notifier.infrastucture;

import com.hexagonal.shop.notifier.application.EmailNotifierUseCase;
import com.hexagonal.shop.shared.domain.bus.event.DomainEventSubscriber;
import com.hexagonal.shop.shared.domain.cart.PurchaseConfirmedDomainEvent;
import org.springframework.context.event.EventListener;

@DomainEventSubscriber({PurchaseConfirmedDomainEvent.class})
public class NotifyOnPurchaseConfirmed {

    private final EmailNotifierUseCase emailNotifierUseCase;

    public NotifyOnPurchaseConfirmed(EmailNotifierUseCase emailNotifierUseCase) {
        this.emailNotifierUseCase = emailNotifierUseCase;
    }


    @EventListener
    public void on(PurchaseConfirmedDomainEvent event) {
        emailNotifierUseCase.sendNotification(event.getEmail());
    }
}
