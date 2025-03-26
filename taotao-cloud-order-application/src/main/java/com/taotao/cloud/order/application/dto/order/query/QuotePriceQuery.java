package com.taotao.cloud.order.application.dto.order.query;

import com.taotao.cloud.order.domain.order.valueobject.detail.OrderDetail;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import static lombok.AccessLevel.PRIVATE;
import com.taotao.boot.ddd.model.application.dto.Query;
@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class QuotePriceQuery extends Query {

    @Valid
    @NotNull
    private final OrderDetail detail;
}
