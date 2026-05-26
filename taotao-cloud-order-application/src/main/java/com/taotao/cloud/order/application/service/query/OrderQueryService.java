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

package com.taotao.cloud.order.application.service.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.boot.common.model.result.PageResult;
import com.taotao.cloud.order.application.dto.cart.result.OrderExportResult;
import com.taotao.cloud.order.application.dto.order.query.*;
import com.taotao.cloud.order.application.dto.order.result.*;
import com.taotao.cloud.order.domain.valobj.OrderStatus;
import com.taotao.cloud.order.domain.entity.Order;
import com.taotao.cloud.order.domain.valobj.Traces;
import com.taotao.cloud.order.domain.valobj.User;
import com.taotao.cloud.order.application.dto.order.result.OrderResult;
import java.math.BigDecimal;
import java.util.List;

public interface OrderQueryService {

    QPriceQuotation quoteOrderPrice(QuotePriceQuery queryCommand, User user);

    OrderStatus fetchOrderStatus(String orderId, User user);

    List<QListOrder> listOrders(ListOrdersQuery queryCommand, User user);

    QDetailedOrder fetchDetailedOrder(String orderId, User user);

    QOrderShipment fetchOrderShipment(String orderId, User user);

    PageResult<OrderSimpleResult> pageQuery(OrderPageQuery orderPageQuery);

    List<OrderResult> queryListByParams(OrderPageQuery orderPageQuery);

    List<OrderResult> queryListByPromotion(String orderPromotionType, String payStatus, String parentOrderSn, String orderSn);

    long queryCountByPromotion(String orderPromotionType, String payStatus, String parentOrderSn, String orderSn);

    List<OrderResult> queryListByPromotion(Long pintuanId);

    List<OrderExportResult> queryExportOrder(OrderPageQuery orderPageQuery);

    Order queryBySn(String orderSn);

    OrderDetailResult queryDetail(String orderSn);

    Traces queryTraces(String orderSn);

    void queryOrderByVerificationCode(String verificationCode);

    List<OrderResult> queryByTradeSn(String tradeSn);

    BigDecimal queryPaymentTotal(String orderSn);

    IPage<PaymentLogResult> queryPaymentLogs(IPage<PaymentLogResult> page, QueryWrapper<PaymentLogResult> queryWrapper);
}
