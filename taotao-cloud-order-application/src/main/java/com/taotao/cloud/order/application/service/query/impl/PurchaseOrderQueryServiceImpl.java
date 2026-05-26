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

package com.taotao.cloud.order.application.service.query.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.cloud.order.application.dto.purchase.PurchaseOrderSearchParams;
import com.taotao.cloud.order.application.dto.purchase.result.PurchaseOrderResult;
import com.taotao.cloud.order.application.service.query.PurchaseOrderQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class PurchaseOrderQueryServiceImpl implements PurchaseOrderQueryService {

    @Override
    public PurchaseOrderResult queryPurchaseOrder(String id) {
        return null;
    }

    @Override
    public IPage<PurchaseOrderResult> page(PurchaseOrderSearchParams purchaseOrderSearchParams) {
        return null;
    }
}
