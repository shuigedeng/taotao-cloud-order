package com.taotao.cloud.order.api.feign.dto.request;

import com.taotao.boot.common.model.request.RequestBase;
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
public class OrderItemSaveApiRequest extends RequestBase {
	private String orderSn;
}
