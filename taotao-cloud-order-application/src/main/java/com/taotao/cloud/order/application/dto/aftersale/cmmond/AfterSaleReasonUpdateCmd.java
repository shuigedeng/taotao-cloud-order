/*
 * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.taotao.cloud.order.application.dto.aftersale.cmmond;

import com.taotao.cloud.order.api.enums.trade.AfterSaleTypeEnum;
import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;

/**
 * 售后原因dto
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 09:16:53
 */
@RecordBuilder
@Schema(description = "售后原因dto")
public record AfterSaleReasonUpdateCmd(
        @Schema(description = "售后原因") String reason,

        /**
         * @see AfterSaleTypeEnum
         */
        @Schema(description = "售后类型") String serviceType)
        implements Serializable {

    @Serial
    private static final long serialVersionUID = 8808470688518188146L;
}
