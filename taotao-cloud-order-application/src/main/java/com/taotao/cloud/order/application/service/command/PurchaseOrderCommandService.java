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

package com.taotao.cloud.order.application.service.command;

import com.taotao.boot.ddd.model.application.service.CommandService;
import com.taotao.cloud.order.application.dto.purchase.result.PurchaseOrderResult;

/**
 * 采购单业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:55:29
 */
public interface PurchaseOrderCommandService extends CommandService {

    /**
     * 添加采购单
     *
     * @param purchaseOrderResult 采购单
     * @return {@link PurchaseOrderResult }
     * @since 2022-04-28 08:55:29
     */
    PurchaseOrderResult addPurchaseOrder(PurchaseOrderResult purchaseOrderResult);

    /**
     * 关闭供求单
     *
     * @param id 供求单ID
     * @return boolean
     * @since 2022-04-28 08:55:29
     */
    boolean close(String id);
}
