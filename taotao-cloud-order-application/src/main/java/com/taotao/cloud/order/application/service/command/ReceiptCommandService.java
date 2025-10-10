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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.boot.ddd.model.application.service.CommandService;
import com.taotao.cloud.order.application.dto.order.command.OrderReceiptAddCommand;
import com.taotao.cloud.order.application.dto.order.query.ReceiptPageQuery;

/**
 * 发票业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:54:50
 */
public interface ReceiptCommandService extends CommandService {

    /**
     * 根据条件获取发票信息列表
     *
     * @param receiptPageQry 发票查询参数
     * @return {@link IPage }<{@link OrderReceiptAddCommand }>
     * @since 2022-04-28 08:54:50
     */
    IPage<OrderReceiptAddCommand> pageQuery(ReceiptPageQuery receiptPageQry);

    /**
     * 根据订单编号获取发票信息
     *
     * @param orderSn 订单编号
     * @return {@link ReceiptPO }
     * @since 2022-04-28 08:54:50
     */
    //    ReceiptPO getByOrderSn(String orderSn);

    /**
     * 获取发票详情
     *
     * @param id 发票id
     * @return {@link ReceiptPO }
     * @since 2022-04-28 08:54:50
     */
    //    ReceiptPO getDetail(String id);

    /**
     * 保存发票
     *
     * @param receiptPO 发票信息
     * @return {@link Boolean }
     * @since 2022-04-28 08:54:50
     */
    //    Boolean saveReceipt(ReceiptPO receiptPO);

    /**
     * 开具发票
     *
     * @param receiptId 发票id
     * @return {@link ReceiptPO }
     * @since 2022-04-28 08:54:50
     */
    //    ReceiptPO invoicing(Long receiptId);
}
