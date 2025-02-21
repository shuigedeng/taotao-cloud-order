package com.taotao.cloud.order.domain.order.valueobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class OrderPrice {
	String originalUpgradePrice;
	String originalRenewalPrice;
	String originalTotalPrice;

	String deliveryFee;

	String discount;
	String discountOffsetPrice;
	String discountedTotalPrice;

}
