package com.taotao.cloud.order.domain.order.event;

import com.taotao.cloud.order.domain.order.valueobject.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

import static lombok.AccessLevel.PRIVATE;

@Getter
@TypeAlias("ORDER_INVOICE_ISSUED_EVENT")
@NoArgsConstructor(access = PRIVATE)
public class OrderInvoiceIssuedEvent extends OrderUpdatedEvent {

    public OrderInvoiceIssuedEvent(Long orderId, User user) {
//        super(ORDER_INVOICE_ISSUED, orderId, user);
    }
}
