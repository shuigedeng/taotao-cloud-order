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

package com.taotao.cloud.order.application.command.dept.executor.query;

import com.taotao.cloud.order.application.command.dept.dto.DeptIdsGetQry;
import com.taotao.cloud.order.infrastructure.persistent.dept.mapper.DeptMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 查看部门IDS执行器.
 */
@Component
@RequiredArgsConstructor
public class DeptIdsGetQryExe {

    private final DeptMapper deptMapper;

    /**
     * 执行查看部门IDS.
     *
     * @param qry 查看部门IDS参数
     * @return 部门IDS
     */
    // @DS(TENANT)
    public List<Long> execute(DeptIdsGetQry qry) {
        return deptMapper.selectIdsByRoleId(qry.getRoleId());
    }
}
