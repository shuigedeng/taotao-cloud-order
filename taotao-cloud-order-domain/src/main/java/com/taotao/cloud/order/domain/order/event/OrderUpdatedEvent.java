package com.taotao.cloud.order.domain.order.event;

import com.taotao.boot.ddd.model.domain.event.DomainEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class OrderUpdatedEvent extends DomainEvent {
    private String orderId;

//    public OrderUpdatedEvent(DomainEventType type, String orderId, User user) {
//        super(type, user);
//        this.orderId = orderId;
//    }
}
