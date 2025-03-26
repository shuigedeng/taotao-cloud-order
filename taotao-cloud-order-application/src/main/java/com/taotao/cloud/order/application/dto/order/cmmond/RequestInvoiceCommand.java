package com.taotao.cloud.order.application.dto.order.cmmond;

import com.taotao.boot.ddd.model.application.dto.Command;
import com.taotao.cloud.order.domain.order.valueobject.invoice.InvoiceType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class RequestInvoiceCommand extends Command {

	@NotNull
	private final InvoiceType type;

	@Email
	@NotBlank
	private final String email;

}
