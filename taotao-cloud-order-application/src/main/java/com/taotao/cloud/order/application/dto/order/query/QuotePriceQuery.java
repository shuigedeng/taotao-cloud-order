package com.taotao.cloud.order.application.dto.order.query;

import com.mryqr.common.utils.Query;
import com.mryqr.core.order.domain.detail.OrderDetail;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class QuotePriceQuery implements Query {

    @Valid
    @NotNull
    private final OrderDetail detail;
}
