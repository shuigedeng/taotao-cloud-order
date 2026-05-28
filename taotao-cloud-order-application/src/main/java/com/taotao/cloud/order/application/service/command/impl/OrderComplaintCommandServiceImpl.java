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

import com.taotao.cloud.order.application.dto.order.command.OrderComplaintAddCmd;
import com.taotao.cloud.order.application.dto.order.command.CreateOrderComplaintOperationCommand;
import com.taotao.cloud.order.application.dto.order.command.StoreAppealCommand;
import com.taotao.cloud.order.application.service.command.OrderComplaintCommandService;
import com.taotao.cloud.order.application.dto.order.result.OrderComplaintResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 交易投诉业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:55:04
 */
@RequiredArgsConstructor
@Service
public class OrderComplaintCommandServiceImpl implements OrderComplaintCommandService {
    @Override
    public Boolean updateOrderComplainByStatus(
            CreateOrderComplaintOperationCommand orderComplaintOperationAddCmd) {
        return null;
    }

    @Override
    public Boolean cancel(Long id) {
        return null;
    }

    @Override
    public Boolean appeal(StoreAppealCommand storeAppealDTO) {
        return null;
    }

    @Override
    public OrderComplaintResult addOrderComplain(OrderComplaintAddCmd orderComplaintAddCmd) {
        return null;
    }

    @Override
    public Boolean updateOrderComplain(OrderComplaintResult orderComplaintResult) {
        return null;
    }
}
