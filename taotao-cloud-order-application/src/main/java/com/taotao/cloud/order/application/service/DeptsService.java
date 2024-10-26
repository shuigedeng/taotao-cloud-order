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

package com.taotao.cloud.order.application.service;

import com.taotao.cloud.order.application.command.dept.dto.DeptCreateCmd;
import com.taotao.cloud.order.application.command.dept.dto.DeptGetQry;
import com.taotao.cloud.order.application.command.dept.dto.DeptIdsGetQry;
import com.taotao.cloud.order.application.command.dept.dto.DeptListQry;
import com.taotao.cloud.order.application.command.dept.dto.DeptModifyCmd;
import com.taotao.cloud.order.application.command.dept.dto.DeptRemoveCmd;
import com.taotao.cloud.order.application.command.dept.dto.clientobject.DeptCO;
import java.util.List;

/**
 * 部门管理.
 */
public interface DeptsService {

    /**
     * 查询部门列表.
     *
     * @param qry 查询部门列表参数
     * @return 部门列表
     */
    List<DeptCO> findList(DeptListQry qry);

    /**
     * 新增部门.
     *
     * @param cmd 新增部门参数
     */
    void create(DeptCreateCmd cmd);

    /**
     * 修改部门.
     *
     * @param cmd 修改部门参数
     */
    void modify(DeptModifyCmd cmd);

    /**
     * 根据IDS删除部门.
     *
     * @param cmd 根据IDS删除部门参数
     */
    void remove(DeptRemoveCmd cmd);

    /**
     * 根据ID查看部门.
     *
     * @param qry 根据ID查看部门参数
     * @return 部门
     */
    DeptCO findById(DeptGetQry qry);

    /**
     * 根据角色ID查看部门IDS.
     *
     * @param qry 根据角色ID查看部门IDS参数
     * @return 部门IDS
     */
    List<Long> findIds(DeptIdsGetQry qry);
}
