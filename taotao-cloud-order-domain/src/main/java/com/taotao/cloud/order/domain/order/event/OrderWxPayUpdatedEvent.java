package com.taotao.cloud.order.domain.order.event;

import com.taotao.cloud.order.domain.order.valueobject.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

import static lombok.AccessLevel.PRIVATE;

@Getter
@TypeAlias("ORDER_WX_PAY_UPDATED_EVENT")
@NoArgsConstructor(access = PRIVATE)
public class OrderWxPayUpdatedEvent extends OrderUpdatedEvent {

    public OrderWxPayUpdatedEvent(Long orderId, User user) {
//        super(ORDER_WX_PAY_UPDATED, orderId, user);
    }
}
