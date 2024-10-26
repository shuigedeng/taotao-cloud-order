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

package com.taotao.cloud.order.application.command.dict.dto.clientobject;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Schema(name = "DictCO", description = "字典")
public class DictCO {

    @Schema(name = "id", description = "ID")
    private Long id;

    @Schema(name = "label", description = "字典标签")
    private String label;

    @Schema(name = "type", description = "字典类型")
    private String type;

    @Schema(name = "value", description = "字典值")
    private String value;

    @Schema(name = "remark", description = "字典备注")
    private String remark;

    @Schema(name = "createDate", description = "创建时间")
    private LocalDateTime createDate;

    @Schema(name = "sort", description = "字典排序")
    private Integer sort;
}
