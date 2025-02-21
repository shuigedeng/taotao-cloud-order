package com.taotao.cloud.order.domain.order.valueobject.delivery;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class Consignee  {
    @NotBlank
    private final String id;

    @NotBlank
    @Size(max = 500)
    private final String name;

    @NotBlank
    private final String mobile;

    @Valid
    @NotNull
    private final Address address;

}
