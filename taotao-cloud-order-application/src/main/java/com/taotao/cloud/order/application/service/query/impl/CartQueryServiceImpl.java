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

import com.taotao.cloud.order.application.service.query.CartQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class CartQueryServiceImpl implements CartQueryService {

    @Override
    public TradeDTO readDTO(CartTypeEnum checkedWay) {
        return null;
    }

    @Override
    public TradeDTO queryCheckedTradeDTO(CartTypeEnum way) {
        return null;
    }

    @Override
    public Long queryCanUseCoupon(CartTypeEnum checkedWay) {
        return null;
    }

    @Override
    public TradeDTO queryAllTradeDTO() {
        return null;
    }

    @Override
    public Long queryCartNum(Boolean checked) {
        return null;
    }
}
