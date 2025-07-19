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

package com.taotao.cloud.order.application.service.aftersale;

import com.taotao.boot.ddd.model.application.service.CommandService;

/**
 * 售后原因业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:49:03
 */
public interface AfterSaleReasonQueryService extends CommandService {

    /**
     * 获取售后原因列表
     *
     * @param serviceType 售后类型
     * @return {@link List }<{@link AfterSaleReasonPO }>
     * @since 2022-04-28 08:49:03
     */
    //    List<AfterSaleReasonPO> afterSaleReasonList(String serviceType);

    /**
     * 修改售后原因
     *
     * @param afterSaleReasonPO 售后原因
     * @return {@link Boolean }
     * @since 2022-04-28 08:49:03
     */
    //    Boolean editAfterSaleReason(AfterSaleReasonPO afterSaleReasonPO);

    /**
     * 分页查询售后原因
     *
     * @param afterSaleReasonPageQry 查询条件
     * @return {@link IPage }<{@link AfterSaleReasonPO }>
     * @since 2022-04-28 08:49:03
     */
    //    IPage<AfterSaleReasonPO> pageQuery(AfterSaleReasonPageQry afterSaleReasonPageQry);
}
