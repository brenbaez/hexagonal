package com.hexagonal.shop.shared.domain.bus.event;

import com.hexagonal.shop.shared.Utils;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Getter
public abstract class DomainEvent {
    private String aggregateId;
    private String eventId;
    private String occurredOn;

    protected DomainEvent(String aggregateId) {
        this.aggregateId = aggregateId;
        this.eventId = UUID.randomUUID().toString();
        this.occurredOn = Utils.dateToString(LocalDateTime.now());
    }

    protected DomainEvent(String aggregateId, String eventId, String occurredOn) {
        this.aggregateId = aggregateId;
        this.eventId = eventId;
        this.occurredOn = occurredOn;
    }

    public abstract Map<String, Serializable> toPrimitives();

    public abstract DomainEvent fromPrimitives(
            String aggregateId,
            Map<String, Serializable> body,
            String eventId,
            String occurredOn
    );

}