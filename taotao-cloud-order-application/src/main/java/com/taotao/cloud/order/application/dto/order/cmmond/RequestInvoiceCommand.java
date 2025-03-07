package com.taotao.cloud.order.application.dto.order.cmmond;

import com.mryqr.common.utils.Command;
import com.mryqr.common.validation.email.Email;
import com.mryqr.core.order.domain.invoice.InvoiceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class RequestInvoiceCommand implements Command {

    @NotNull
    private final InvoiceType type;

    @Email
    @NotBlank
    private final String email;

}
