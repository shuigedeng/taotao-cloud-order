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

import static com.taotao.cloud.order.domain.valobj.OrderStatus.PAID;
import static com.taotao.cloud.order.domain.valobj.OrderStatus.REFUNDED;
import static lombok.AccessLevel.PRIVATE;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.boot.common.model.result.PageResult;
import com.taotao.cloud.order.application.dto.cart.result.OrderExportResult;
import com.taotao.cloud.order.application.dto.order.query.*;
import com.taotao.cloud.order.application.dto.order.result.*;
import com.taotao.cloud.order.application.service.query.OrderQueryService;
import com.taotao.cloud.order.domain.aggregate.OrderAgg;
import com.taotao.cloud.order.domain.entity.Order;
import com.taotao.cloud.order.domain.repository.OrderRepository;
import com.taotao.cloud.order.domain.valobj.OrderStatus;
import com.taotao.cloud.order.domain.valobj.Traces;
import com.taotao.cloud.order.domain.valobj.User;
import com.taotao.cloud.order.application.dto.order.result.OrderResult;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderQueryServiceImpl implements OrderQueryService {

    private static final List<OrderStatus> VIEWABLE_ORDER_STATUSES = List.of(PAID, REFUNDED);
    private static final String SHIPMENT_BASE_URL = "https://wuliu.market.alicloudapi.com/kdi";
    private final OrderRepository orderRepository;

    public QPriceQuotation quoteOrderPrice( QuotePriceQuery queryCommand, User user ) {
        user.checkIsTenantAdmin();

        return QPriceQuotation.builder()
                .build();
    }

    public OrderStatus fetchOrderStatus( String orderId, User user ) {
        user.checkIsTenantAdmin();

        OrderAgg order = orderRepository.byIdAndCheckTenantShip(orderId, user);
        return order.getStatus();
    }

    public List<QListOrder> listOrders(ListOrdersQuery queryCommand, User user) {
        user.checkIsTenantAdmin();
        return List.of();
    }

    public QDetailedOrder fetchDetailedOrder( String orderId, User user ) {
        user.checkIsTenantAdmin();

        OrderAgg order = orderRepository.byIdAndCheckTenantShip(orderId, user);

        return QDetailedOrder.builder()
                .description(order.description())
                .orderDetailType(order.getDetail().getType().getName())
                .orderDetail(order.getDetail())
                .status(order.getStatus().getName())
                .discountedTotalPrice(order.getPrice().getDiscountedTotalPrice())
                .paymentType(order.getPaymentType().getName())
                .wxTxnId(order.getWxTxnId())
                .bankTransferCode(order.getBankTransferCode())
                .bankName(order.getBankName())
                .bankTransferAccountId(order.getBankTransferAccountId())
                .paidAt(order.getPaidAt())
                .invoiceRequested(order.isInvoiceRequested())
                .invoiceIssued(order.isInvoiceIssued())
                .invoiceTitle(
                        order.isInvoiceRequested()
                                ? order.getInvoice().getTitle().getTitle()
                                : null)
                .invoiceType(
                        order.isInvoiceRequested() ? order.getInvoice().getType().getName() : null)
                .invoiceEmail(order.isInvoiceRequested() ? order.getInvoice().getEmail() : null)
                .carrier(
                        order.getDelivery() != null
                                ? order.getDelivery().carrier().getName()
                                : null)
                .deliveryOrderId(
                        order.getDelivery() != null
                                ? order.getDelivery().deliveryOrderId()
                                : null)
                .build();
    }

    public QOrderShipment fetchOrderShipment( String orderId, User user ) {
        user.checkIsTenantAdmin();

        OrderAgg order = orderRepository.byIdAndCheckTenantShip(orderId, user);

        RawShipmentResult result = null;
        return QOrderShipment.builder()
                .orderId(orderId)
                .nodes(
                        result.getList().stream()
                                .map(
                                        node ->
                                                ShipmentNodeQuery.builder()
                                                        .time(node.getTime())
                                                        .status(node.getStatus())
                                                        .build())
                                .toList())
                .signed(Objects.equals(result.getIssign(), "1"))
                .deliveryStatus(result.getDeliverystatus())
                .carrierName(result.getExpName())
                .carrierLogo(result.getLogo())
                .updateTime(result.getUpdateTime())
                .build();
    }

    @Override
    public Order queryBySn(String orderSn) {
        return null;
    }

    @Override
    public PageResult<OrderSimpleResult> pageQuery(OrderPageQuery orderPageQuery) {
        return null;
    }

    @Override
    public List<OrderResult> queryListByParams(OrderPageQuery orderPageQuery) {
        return List.of();
    }

    @Override
    public List<OrderResult> queryListByPromotion(String orderPromotionType, String payStatus, String parentOrderSn, String orderSn) {
        return List.of();
    }

    @Override
    public long queryCountByPromotion(String orderPromotionType, String payStatus, String parentOrderSn, String orderSn) {
        return 0;
    }

    @Override
    public List<OrderResult> queryListByPromotion(Long pintuanId) {
        return List.of();
    }

    @Override
    public List<OrderExportResult> queryExportOrder(OrderPageQuery orderPageQuery) {
        return List.of();
    }

    @Override
    public OrderDetailResult queryDetail(String orderSn) {
        return null;
    }

    @Override
    public Traces queryTraces(String orderSn) {
        return null;
    }

    @Override
    public void queryOrderByVerificationCode(String verificationCode) {

    }

    @Override
    public List<OrderResult> queryByTradeSn(String tradeSn) {
        return List.of();
    }

    @Override
    public BigDecimal queryPaymentTotal(String orderSn) {
        return null;
    }

    @Override
    public IPage<PaymentLogResult> queryPaymentLogs(IPage<PaymentLogResult> page, QueryWrapper<PaymentLogResult> queryWrapper) {
        return null;
    }

    @Value
    @Builder
    @RequiredArgsConstructor(access = PRIVATE)
    private static class RawShipment {

        private final String status;
        private final String msg;
        private final RawShipmentResult result;
    }

    @Value
    @Builder
    @RequiredArgsConstructor(access = PRIVATE)
    private static class RawShipmentResult {

        private final String number;
        private final String type;
        private final List<RawShipmentNode> list;
        private final String deliverystatus;
        private final String issign;
        private final String expName;
        private final String logo;
        private final String updateTime;
    }

    @Value
    @Builder
    @RequiredArgsConstructor(access = PRIVATE)
    private static class RawShipmentNode {

        private final String time;
        private final String status;
    }
}
