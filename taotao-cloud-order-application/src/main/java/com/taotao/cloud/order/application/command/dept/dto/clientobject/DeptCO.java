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

package com.taotao.cloud.order.application.command.dept.dto.clientobject;

import static lombok.AccessLevel.PRIVATE;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
@Schema(name = "DeptCO", description = "部门")
public class DeptCO {

    @Serial
    private static final long serialVersionUID = 4116703987840123059L;

    @Schema(name = "id", description = "ID")
    private Long id;

    @Schema(name = "name", description = "名称")
    private String name;

    @Schema(name = "pid", description = "父节点ID")
    private Long pid;

    @Schema(name = "path", description = "节点PATH")
    private String path;

    @Schema(name = "children", description = "子节点")
    private List<DeptCO> children = new ArrayList<>(16);

    @Schema(name = "sort", description = "部门排序")
    private Integer sort;
}
