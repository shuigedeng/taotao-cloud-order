package com.taotao.cloud.order.application.dto.common;

import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;

@RecordBuilder
@Schema(description = "物流踪迹结果")
public record TracesResult(
        @Schema(description = "物流公司") String shipper,
        @Schema(description = "物流单号") String logisticCode,
        @Schema(description = "物流轨迹") Object traces)
        implements Serializable {

    @Serial
    private static final long serialVersionUID = 8808470688518188146L;
}
