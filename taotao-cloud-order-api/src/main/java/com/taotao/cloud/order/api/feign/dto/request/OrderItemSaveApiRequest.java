package com.taotao.cloud.order.api.feign.dto.request;

import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Setter
@Getter
@ToString
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemSaveApiRequest {
	private String orderSn;
}
