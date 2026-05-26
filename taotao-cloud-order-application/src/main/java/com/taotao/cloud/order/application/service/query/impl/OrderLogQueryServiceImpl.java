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
import com.taotao.cloud.order.application.dto.order.query.OrderLogPageQuery;
import com.taotao.cloud.order.application.dto.order.result.OrderLogResult;
import com.taotao.cloud.order.application.service.query.OrderLogQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLogQueryServiceImpl implements OrderLogQueryService {

    @Override
    public List<OrderLogResult> getOrderLog(String orderSn) {
        return null;
    }

    @Override
    public IPage<OrderLogResult> pageQuery(OrderLogPageQuery orderLogPageQuery) {
        return null;
    }

    @Override
    public List<OrderLogResult> queryOrderLog(String orderSn) {
        return null;
    }

    @Override
    public OrderLogResult queryById(String id) {
        return null;
    }
}
