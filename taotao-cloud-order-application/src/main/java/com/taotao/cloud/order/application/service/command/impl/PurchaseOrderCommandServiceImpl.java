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

import com.taotao.cloud.order.application.dto.purchase.result.PurchaseOrderResult;
import com.taotao.cloud.order.application.service.command.PurchaseOrderCommandService;
import org.springframework.stereotype.Service;

/**
 * 采购单业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:55:40
 */
@Service
public class PurchaseOrderCommandServiceImpl implements PurchaseOrderCommandService {

    @Autowired
    private PurchaseOrderItemCommandService purchaseOrderItemCommandService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PurchaseOrderResult addPurchaseOrder(PurchaseOrderResult purchaseOrderResult) {
       PurchaseOrderPO purchaseOrderPO = new PurchaseOrderPO();
       BeanUtil.copyProperties(purchaseOrderResult, purchaseOrderPO);
       purchaseOrderPO.setStatus("OPEN");
       purchaseOrderPO.setMemberId(UserContext.getCurrentUser().getId());
       this.save(purchaseOrderPO);
       purchaseOrderItemCommandService.addPurchaseOrderItem(purchaseOrderPO.getId(),
            purchaseOrderResult.getPurchaseOrderItems());
       return purchaseOrderResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean close(String id) {
       PurchaseOrderPO purchaseOrderPO = this.getById(id);
       purchaseOrderPO.setStatus("CLOSE");
       UpdateWrapper<PurchaseOrderPO> updateWrapper = new UpdateWrapper<>();
       updateWrapper.eq("id", id);
       updateWrapper.set("status", "CLOSE");
       return this.update(updateWrapper);
    }
}
