package com.taotao.cloud.order.application.dto.order.query;

import com.taotao.cloud.order.domain.order.valueobject.OrderPrice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class QPriceQuotation {
    private final OrderPrice price;
}
