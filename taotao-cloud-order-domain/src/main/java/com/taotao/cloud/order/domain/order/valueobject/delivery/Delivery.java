package com.taotao.cloud.order.domain.order.valueobject.delivery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class Delivery {
    private final Carrier carrier;
    private final String deliveryOrderId;
}
