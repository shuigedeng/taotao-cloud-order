package com.taotao.cloud.order.domain.order.event;

import com.taotao.cloud.order.domain.order.valueobject.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

import static lombok.AccessLevel.PRIVATE;

@Getter
@TypeAlias("ORDER_DELIVERY_UPDATED_EVENT")
@NoArgsConstructor(access = PRIVATE)
public class OrderDeliveryUpdatedEvent extends OrderUpdatedEvent {

    public OrderDeliveryUpdatedEvent(Long orderId, User user) {
//        super(ORDER_DELIVERY_UPDATED, orderId, user);
    }
}
