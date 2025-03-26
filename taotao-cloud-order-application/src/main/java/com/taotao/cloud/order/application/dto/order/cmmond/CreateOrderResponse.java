package com.taotao.cloud.order.application.dto.order.cmmond;

import com.taotao.cloud.order.domain.order.valueobject.OrderPrice;
import com.taotao.cloud.order.domain.order.valueobject.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class CreateOrderResponse {
	private final Long id;
	private final PaymentType paymentType;
	private final String wxPayQrUrl;
	private final String bankTransferCode;
	private final OrderPrice price;
	private final String payDescription;
	private final Instant createdAt;
}
