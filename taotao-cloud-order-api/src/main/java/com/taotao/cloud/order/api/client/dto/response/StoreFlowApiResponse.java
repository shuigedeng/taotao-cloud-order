package com.taotao.cloud.order.api.client.dto.response;

import lombok.*;
import lombok.experimental.Accessors;

@Setter
@Getter
@ToString
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
public class StoreFlowApiResponse {
	private String orderSn;
}
