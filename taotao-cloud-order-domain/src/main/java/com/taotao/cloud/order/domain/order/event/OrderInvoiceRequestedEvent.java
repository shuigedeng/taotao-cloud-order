package com.taotao.cloud.order.domain.order.event;

import com.taotao.cloud.order.domain.order.valueobject.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

import static lombok.AccessLevel.PRIVATE;

@Getter
@TypeAlias("ORDER_INVOICE_REQUESTED_EVENT")
@NoArgsConstructor(access = PRIVATE)
public class OrderInvoiceRequestedEvent extends OrderUpdatedEvent {

    public OrderInvoiceRequestedEvent(Long orderId, User user) {
//        super(ORDER_INVOICE_REQUESTED, orderId, user);
    }
}
