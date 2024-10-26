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

package com.taotao.cloud.order.application.command.dept.executor;

import com.taotao.cloud.order.application.command.dept.dto.DeptModifyCmd;
import com.taotao.cloud.order.application.command.dept.dto.clientobject.DeptCO;
import com.taotao.cloud.order.domain.dept.entity.DeptEntity;
import com.taotao.cloud.order.domain.dept.service.DeptDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 修改部门执行器.
 *
 */
@Component
@RequiredArgsConstructor
public class DeptModifyCmdExe {

    private final DeptDomainService deptDomainService;

    /**
     * 执行修改部门.
     * @param cmd 修改部门参数
     */
    // @DS(TENANT)
    public void executeVoid(DeptModifyCmd cmd) {
        deptDomainService.modify(convert(cmd.getDeptCO()));
    }

    private DeptEntity convert(DeptCO deptCO) {
        return null;
        //		return DeptEntity.builder()
        //			.id(deptCO.getId())
        //			.pid(deptCO.getPid())
        //			.name(deptCO.getName())
        //			.sort(deptCO.getSort())
        //			.build();
    }
}
