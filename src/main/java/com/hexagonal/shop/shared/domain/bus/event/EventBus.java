package com.hexagonal.shop.shared.domain.bus.event;

import java.util.List;

public interface EventBus<T extends DomainEvent>{
    void publish(final List<DomainEvent> events);
}
