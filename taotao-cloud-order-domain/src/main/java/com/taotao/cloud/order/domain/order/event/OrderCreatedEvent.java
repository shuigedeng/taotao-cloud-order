package com.taotao.cloud.order.domain.order.event;

import com.taotao.boot.ddd.model.domain.event.DomainEvent;
import com.taotao.cloud.order.domain.order.valueobject.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

import static lombok.AccessLevel.PRIVATE;

@Getter
@TypeAlias("ORDER_CREATED_EVENT")
@NoArgsConstructor(access = PRIVATE)
public class OrderCreatedEvent extends DomainEvent {
    private Long orderId;

    public OrderCreatedEvent(Long orderId, User user) {
//        super(ORDER_CREATED, user);
        this.orderId = orderId;
    }
}
