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

package com.taotao.cloud.order.application.service.command.impl;

import com.taotao.cloud.order.application.dto.purchase.result.PurchaseOrderItemResult;
import com.taotao.cloud.order.application.service.command.PurchaseOrderItemCommandService;
import org.springframework.stereotype.Service;

/**
 * 采购单子内容业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:55:37
 */
@Service
public class PurchaseOrderItemCommandServiceImpl implements PurchaseOrderItemCommandService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addPurchaseOrderItem(String purchaseOrderId,
                                        List<PurchaseOrderItemResult> purchaseOrderItemResultList) {
        for (PurchaseOrderItemResult purchaseOrderItemResult : purchaseOrderItemResultList) {
            purchaseOrderItemResult.setPurchaseOrderId(purchaseOrderId);
            this.save(purchaseOrderItemResult);
        }
        return true;
    }
}
