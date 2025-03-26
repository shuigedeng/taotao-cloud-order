package com.taotao.cloud.order.domain.order.event;

import com.taotao.cloud.order.domain.order.valueobject.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

import static lombok.AccessLevel.PRIVATE;

@Getter
@TypeAlias("ORDER_REFUND_UPDATE_EVENT")
@NoArgsConstructor(access = PRIVATE)
public class OrderRefundUpdatedEvent extends OrderUpdatedEvent {

    public OrderRefundUpdatedEvent(Long orderId, User user) {
//        super(ORDER_REFUND_UPDATED, orderId, user);
    }
}
