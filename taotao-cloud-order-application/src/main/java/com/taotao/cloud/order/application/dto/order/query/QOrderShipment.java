package com.taotao.cloud.order.application.dto.order.query;

import com.taotao.cloud.order.domain.order.valueobject.delivery.Carrier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class QOrderShipment {
    private final String orderId;
    private final Carrier carrier;
    private final String deliveryOrderId;
    private final List<ShipmentNode> nodes;
    private final boolean signed;
    private final String deliveryStatus;
    private final String carrierName;
    private final String carrierLogo;
    private final String updateTime;
}
