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
import com.taotao.cloud.order.application.dto.order.command.OrderComplaintAddCmd;
import com.taotao.cloud.order.application.dto.order.command.OrderComplaintOperationAddCommand;
import com.taotao.cloud.order.application.dto.order.command.StoreAppealCommand;
import com.taotao.cloud.order.application.dto.order.result.OrderComplaintResult;

/**
 * 交易投诉业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:54:36
 */
public interface OrderComplaintCommandService extends CommandService {

    /**
     * 添加交易投诉
     *
     * @param orderComplaintAddCmd 交易投诉信息
     * @return {@link OrderComplaintPO }
     * @since 2022-04-28 08:54:36
     */
    OrderComplaintResult addOrderComplain(OrderComplaintAddCmd orderComplaintAddCmd);

    /**
     * 更新交易投诉
     *
     * @param orderComplaintResult 交易投诉信息
     * @return {@link Boolean }
     * @since 2022-04-28 08:54:36
     */
    Boolean updateOrderComplain(OrderComplaintResult orderComplaintResult);

    /**
     * 修改交易投诉状态
     *
     * @param orderComplaintOperationAddCmd 订单投诉操作dto
     * @return {@link Boolean }
     * @since 2022-04-28 08:54:36
     */
    Boolean updateOrderComplainByStatus(
            OrderComplaintOperationAddCommand orderComplaintOperationAddCmd);

    /**
     * 取消交易投诉
     *
     * @param id 交易投诉ID
     * @return {@link Boolean }
     * @since 2022-04-28 08:54:37
     */
    Boolean cancel(Long id);

    /**
     * 店铺申诉
     *
     * @param storeAppealDTO 商店吸引力dto
     * @return {@link Boolean }
     * @since 2022-04-28 08:54:37
     */
    Boolean appeal(StoreAppealCommand storeAppealDTO);
}
