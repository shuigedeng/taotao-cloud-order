package com.taotao.cloud.order.application.dto.order.cmmond;

import com.taotao.boot.ddd.model.application.dto.Command;
import com.taotao.cloud.order.domain.order.valueobject.PaymentType;
import com.taotao.cloud.order.domain.order.valueobject.detail.OrderDetail;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class CreateOrderCommand extends Command {
	@Valid
	@NotNull
	private final OrderDetail detail;

	@NotNull
	private final PaymentType paymentType;
}
