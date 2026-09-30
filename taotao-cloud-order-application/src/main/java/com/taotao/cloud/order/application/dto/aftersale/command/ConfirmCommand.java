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

package com.taotao.cloud.order.application.dto.aftersale.command;

import io.soabase.recordbuilder.core.RecordBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 售后原因dto
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 09:16:53
 */
@RecordBuilder
@Schema(description = "售后原因dto")
public record ConfirmCommand(
	@NotNull(message = "请选择售后单") String afterSaleSn,
	@NotNull(message = "请审核") String serviceStatus,
	String remark)
	implements Serializable {

	@Serial
	private static final long serialVersionUID = 8808470688518188146L;
}
