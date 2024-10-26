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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.taotao.cloud.order.application.command.dept.dto.DeptListQry;
import com.taotao.cloud.order.application.command.dept.dto.clientobject.DeptCO;
import com.taotao.cloud.order.common.enums.FindTypeEnums;
import com.taotao.cloud.order.infrastructure.persistent.dept.mapper.DeptMapper;
import com.taotao.cloud.order.infrastructure.persistent.dept.po.DeptPO;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.dromara.hutool.core.text.StrUtil;
import org.springframework.stereotype.Component;

/**
 * 查询部门列表执行器.
 */
@Component
@RequiredArgsConstructor
public class DeptListQryExe {

    private final DeptMapper deptMapper;

    /**
     * 执行查询部门列表.
     *
     * @param qry 查询部门列表参数
     * @return 部门列表
     */
    //	@DS(TENANT)
    public List<DeptCO> execute(DeptListQry qry) {
        return switch (FindTypeEnums.valueOf(qry.getType())) {
            case LIST -> getDeptList(qry).stream().map(this::convert).toList();
            case TREE_LIST -> buildTreeNode(
                    getDeptList(qry).stream().map(this::convert).toList());
            case USER_TREE_LIST -> null;
        };
    }

    private List<DeptCO> buildTreeNode(List<DeptCO> list) {
        //		return TreeUtil.buildTreeNode(list, DeptCO.class).getChildren();
        return new ArrayList<>();
    }

    private DeptCO convert(DeptPO deptPO) {
        return DeptCO.builder()
                .id(deptPO.getId())
                .pid(deptPO.getParentId())
                .name(deptPO.getName())
                .sort(deptPO.getSortNum())
                .children(new ArrayList<>(16))
                .build();
    }

    private List<DeptPO> getDeptList(DeptListQry qry) {
        String name = qry.getName();
        LambdaQueryWrapper<DeptPO> wrapper = Wrappers.lambdaQuery(DeptPO.class)
                .like(StrUtil.isNotEmpty(name), DeptPO::getName, name)
                .orderByDesc(DeptPO::getSortNum)
                .select(DeptPO::getId, DeptPO::getParentId, DeptPO::getName, DeptPO::getSortNum);
        return deptMapper.selectList(wrapper);
    }
}
