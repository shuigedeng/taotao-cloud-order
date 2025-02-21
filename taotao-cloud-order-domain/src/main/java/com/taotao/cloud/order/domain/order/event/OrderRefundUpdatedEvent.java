package com.taotao.cloud.order.domain.order.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

import static lombok.AccessLevel.PRIVATE;

@Getter
@TypeAlias("ORDER_REFUND_UPDATE_EVENT")
@NoArgsConstructor(access = PRIVATE)
public class OrderRefundUpdatedEvent extends OrderUpdatedEvent {

    public OrderRefundUpdatedEvent(String orderId, User user) {
        super(ORDER_REFUND_UPDATED, orderId, user);
    }
}
