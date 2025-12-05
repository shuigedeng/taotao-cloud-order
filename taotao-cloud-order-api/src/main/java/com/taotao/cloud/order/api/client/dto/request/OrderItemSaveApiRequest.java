package com.taotao.cloud.order.api.client.dto.request;

import com.taotao.boot.common.model.request.RequestBase;
import lombok.*;
import lombok.experimental.Accessors;

@Setter
@Getter
@ToString
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemSaveApiRequest extends RequestBase {
	private String orderSn;
}
