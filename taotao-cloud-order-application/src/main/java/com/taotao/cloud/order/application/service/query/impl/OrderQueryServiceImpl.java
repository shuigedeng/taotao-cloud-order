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

import static com.taotao.cloud.order.domain.order.valobj.OrderStatus.PAID;
import static com.taotao.cloud.order.domain.order.valobj.OrderStatus.REFUNDED;
import static lombok.AccessLevel.PRIVATE;

import com.taotao.cloud.order.application.dto.own.order.query.*;
import com.taotao.cloud.order.application.service.query.OrderQueryService;
import com.taotao.cloud.order.domain.order.aggregate.Order;
import com.taotao.cloud.order.domain.order.repository.OrderRepository;
import com.taotao.cloud.order.domain.order.valobj.OrderStatus;
import com.taotao.cloud.order.domain.order.valobj.User;
import java.util.List;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 子订单业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:54:47
 */
@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderQueryServiceImpl implements OrderQueryService {

    private static final List<OrderStatus> VIEWABLE_ORDER_STATUSES = List.of(PAID, REFUNDED);
    private static final String SHIPMENT_BASE_URL = "https://wuliu.market.alicloudapi.com/kdi";
    //	private final TenantRepository tenantRepository;
    //	private final MryRateLimiter mryRateLimiter;
    private final OrderRepository orderRepository;

    //	private final MongoTemplate mongoTemplate;
    //	private final RestTemplate restTemplate;
    //	private final AliyunProperties aliyunProperties;
    //	private final MryObjectMapper objectMapper;

    public QPriceQuotation quoteOrderPrice( QuotePriceQuery queryCommand, User user ) {
        user.checkIsTenantAdmin();
        //		mryRateLimiter.applyFor(user.getTenantId(), "Order:Quote", 5);

        // Tenant tenant = tenantRepository.byId(user.getTenantId());
        // OrderPrice price = queryCommand.getDetail().calculatePrice(tenant);

        return QPriceQuotation.builder()
                // .price(price)
                .build();
    }

    public OrderStatus fetchOrderStatus( String orderId, User user ) {
        user.checkIsTenantAdmin();
        // mryRateLimiter.applyFor(user.getTenantId(), "Order:FetchStatus", 5);

        Order order = orderRepository.byIdAndCheckTenantShip(orderId, user);
        return order.getStatus();
    }

    // public PagedList<QListOrder> listOrders(ListOrdersQuery queryCommand, User user) {
    //	user.checkIsTenantAdmin();
    //	mryRateLimiter.applyFor(user.getTenantId(), "Order:List", 5);
    //
    //	Pagination pagination = pagination(queryCommand.getPageIndex(), queryCommand.getPageSize());
    //	Criteria criteria =
    // where("tenantId").is(user.getTenantId()).and("status").in(VIEWABLE_ORDER_STATUSES);
    //
    //	String search = queryCommand.getSearch();
    //	if (isNotBlank(search)) {
    //		if (isOrderId(search)) {
    //			criteria.and("_id").is(search);
    //		} else {
    //			criteria.orOperator(where("wxTxnId").is(search),
    //				where("bankTransferCode").is(search),
    //				where("bankTransferAccountId").is(search));
    //		}
    //	}
    //
    //	Query query = query(criteria);
    //	long count = mongoTemplate.count(query, Order.class);
    //	if (count == 0) {
    //		return pagedList(pagination, 0, List.of());
    //	}
    //
    //	query.skip(pagination.skip()).limit(pagination.getPageSize()).with(Sort.by(DESC,
    // "createdAt"));
    //
    //	List<Order> orders = mongoTemplate.find(query, Order.class);
    //	List<QListOrder> listOrders = orders.stream()
    //		.map(order -> QListOrder.builder()
    //			.id(order.getId())
    //			.orderDetailTypeEnum(order.getDetail().getType())
    //			.orderDetailType(order.getDetail().getType().getName())
    //			.status(order.getStatus().getName())
    //			.statusEnum(order.getStatus())
    //			.description(order.description())
    //			.paidPrice(order.getPrice().getDiscountedTotalPrice())
    //			.paymentType(order.getPaymentType().getName())
    //			.paidAt(order.getPaidAt())
    //			.createdAt(order.getCreatedAt())
    //			.invoiceRequested(order.isInvoiceRequested())
    //			.invoiceIssued(order.isInvoiceIssued())
    //			.build())
    //		.collect(toImmutableList());
    //
    //	return pagedList(pagination, (int) count, listOrders);
    // }
    //
    // private PagedList<QListOrder> pagedList(Pagination pagination, int count, List<QListOrder>
    // apps) {
    //	return PagedList.<QListOrder>builder()
    //		.totalNumber(count)
    //		.pageIndex(pagination.getPageIndex())
    //		.pageSize(pagination.getPageSize())
    //		.data(apps)
    //		.build();
    // }

    public QDetailedOrder fetchDetailedOrder( String orderId, User user ) {
        user.checkIsTenantAdmin();
        // mryRateLimiter.applyFor(user.getTenantId(), "Order:FetchDetailedOrder", 5);

        Order order = orderRepository.byIdAndCheckTenantShip(orderId, user);

        return QDetailedOrder.builder()
                // .id(order.getId())
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
                                ? order.getDelivery().getCarrier().getName()
                                : null)
                .deliveryOrderId(
                        order.getDelivery() != null
                                ? order.getDelivery().getDeliveryOrderId()
                                : null)
                // .createdAt(order.getCreatedAt())
                // .createdBy(order.getCreatedBy())
                // .creator(order.getCreator())
                .build();
    }

    public QOrderShipment fetchOrderShipment( String orderId, User user ) {
        user.checkIsTenantAdmin();
        // mryRateLimiter.applyFor(user.getTenantId(), "Order:FetchOrderShipment", 5);

        Order order = orderRepository.byIdAndCheckTenantShip(orderId, user);
        // if (order.getDelivery() == null ||
        //	!order.atPaid() ||
        //	order.getCreatedAt().isBefore(now().minus(90, DAYS))) {
        //	return QOrderShipment.builder()
        //		.orderId(order.getId())
        //		.nodes(List.of())
        //		.build();
        // }
        //
        // Delivery delivery = order.getDelivery();
        // HttpHeaders headers = new HttpHeaders();
        // headers.set("Authorization", "APPCODE " + aliyunProperties.getDeliveryQueryAppCode());
        // String url = UriComponentsBuilder.fromUriString(SHIPMENT_BASE_URL)
        //	.queryParam("no", delivery.getDeliveryOrderId())
        //	.queryParam("type", delivery.getCarrier().getType())
        //	.toUriString();
        //
        // ResponseEntity<String> response = restTemplate.exchange(url, GET, new
        // HttpEntity<>(headers), String.class);
        // String responseBody = response.getBody();
        // RawShipment rawShipment = objectMapper.readValue(responseBody, RawShipment.class);
        //
        // if (rawShipment == null || rawShipment.getResult() == null ||
        // isEmpty(rawShipment.getResult().getList())) {
        //	return QOrderShipment.builder()
        //		.orderId(order.getId())
        //		.carrier(delivery.getCarrier())
        //		.deliveryOrderId(delivery.getDeliveryOrderId())
        //		.nodes(List.of())
        //		.build();
        // }

        // RawShipmentResult result = rawShipment.getResult();
        RawShipmentResult result = null;
        return QOrderShipment.builder()
                .orderId(orderId)
                // .carrier(delivery.getCarrier())
                // .deliveryOrderId(delivery.getDeliveryOrderId())
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

    /**
     * RawShipment
     *
     * @author shuigedeng
     * @version 2026.01
     * @since 2025-12-19 09:30:45
     */
    @Value
    @Builder
    @RequiredArgsConstructor(access = PRIVATE)
    private static class RawShipment {

        private final String status;
        private final String msg;
        private final RawShipmentResult result;
    }

    /**
     * RawShipmentResult
     *
     * @author shuigedeng
     * @version 2026.01
     * @since 2025-12-19 09:30:45
     */
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

    /**
     * RawShipmentNode
     *
     * @author shuigedeng
     * @version 2026.01
     * @since 2025-12-19 09:30:45
     */
    @Value
    @Builder
    @RequiredArgsConstructor(access = PRIVATE)
    private static class RawShipmentNode {

        private final String time;
        private final String status;
    }
}
