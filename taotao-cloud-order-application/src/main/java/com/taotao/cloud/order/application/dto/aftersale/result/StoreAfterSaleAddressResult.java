package com.taotao.cloud.order.application.dto.aftersale.result;

import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;

@RecordBuilder
@Schema(description = "店铺售后收件地址")
public record StoreAfterSaleAddressResult(
        @Schema(description = "收货人姓名") String salesConsigneeName,
        @Schema(description = "收货人手机") String salesConsigneeMobile,
        @Schema(description = "地址Id") String salesConsigneeAddressId,
        @Schema(description = "地址Path") String salesConsigneeAddressPath,
        @Schema(description = "详细地址") String salesConsigneeDetail)
        implements Serializable {

    @Serial
    private static final long serialVersionUID = 8808470688518188146L;
}
