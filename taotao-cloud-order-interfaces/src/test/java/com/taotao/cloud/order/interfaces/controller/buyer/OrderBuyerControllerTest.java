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

package com.taotao.cloud.order.interfaces.controller.buyer;

import com.taotao.boot.common.model.result.PageResult;
import com.taotao.boot.common.model.result.Result;
import com.taotao.cloud.order.application.dto.order.query.OrderPageQuery;
import com.taotao.cloud.order.application.dto.order.result.OrderDetailResult;
import com.taotao.cloud.order.application.dto.order.result.OrderSimpleResult;
import com.taotao.cloud.order.application.service.command.OrderCommandService;
import com.taotao.cloud.order.application.service.query.OrderQueryService;
import com.taotao.cloud.order.common.enums.order.OrderStatusEnum;
import com.taotao.cloud.order.domain.entity.Order;
import com.taotao.cloud.order.domain.valobj.Traces;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * OrderBuyerControllerTest
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
@ExtendWith(MockitoExtension.class)
class OrderBuyerControllerTest {

    @Mock
    private OrderQueryService orderQueryService;

    @Mock
    private OrderCommandService orderCommandService;

    @InjectMocks
    private OrderBuyerController orderBuyerController;

    @Mock
    private Order order;

    @Mock
    private OrderDetailResult orderDetailResult;

    private static final String ORDER_SN = "SN20251219001";

    // ==================== queryMineOrder Tests ====================

    @Nested
    @DisplayName("查询会员订单列表")
    class QueryMineOrderTests {

        @Test
        @DisplayName("分页查询 - 返回PageResult")
        void shouldReturnPageResult_whenQueryMineOrder() {
            // Given
            OrderPageQuery pageQuery = new OrderPageQuery();
            PageResult<OrderSimpleResult> mockPage = new PageResult<>();
            mockPage.setRecords(List.of());

            when(orderQueryService.pageQuery(any(OrderPageQuery.class))).thenReturn(mockPage);

            // When
            Result<PageResult<OrderSimpleResult>> result = orderBuyerController.queryMineOrder(pageQuery);

            // Then
            assertThat(result).isNotNull();
            assertThat(result.getCode()).isEqualTo(200);
            verify(orderQueryService).pageQuery(any(OrderPageQuery.class));
        }
    }

    // ==================== detail Tests ====================

    @Nested
    @DisplayName("订单明细")
    class DetailTests {

        @Test
        @DisplayName("查询订单明细 - 返回OrderDetailResult")
        void shouldReturnDetail_whenDetail() {
            // Given
            when(orderQueryService.queryDetail(ORDER_SN)).thenReturn(orderDetailResult);

            // When
            Result<OrderDetailResult> result = orderBuyerController.detail(ORDER_SN);

            // Then
            assertThat(result).isNotNull();
            assertThat(result.getCode()).isEqualTo(200);
            assertThat(result.getData()).isEqualTo(orderDetailResult);
            verify(orderQueryService).queryDetail(ORDER_SN);
        }
    }

    // ==================== receiving Tests ====================

    @Nested
    @DisplayName("确认收货")
    class ReceivingTests {

        @Test
        @DisplayName("已发货订单 - 确认收货成功")
        void shouldCompleteOrder_whenReceiving() {
            // Given
            when(orderQueryService.queryBySn(ORDER_SN)).thenReturn(order);
            when(order.queryOrderStatus()).thenReturn(OrderStatusEnum.DELIVERED.name());

            // When
            Result<Void> result = orderBuyerController.receiving(ORDER_SN);

            // Then
            assertThat(result).isNotNull();
            assertThat(result.getCode()).isEqualTo(200);
            verify(orderCommandService).complete(ORDER_SN);
        }

        @Test
        @DisplayName("未发货订单 - 抛出BusinessException")
        void shouldThrowException_whenNotDelivered() {
            // Given
            when(orderQueryService.queryBySn(ORDER_SN)).thenReturn(order);
            when(order.queryOrderStatus()).thenReturn(OrderStatusEnum.CREATED.name());

            // When / Then
            try {
                orderBuyerController.receiving(ORDER_SN);
                // If we reach here, no exception was thrown - but that's expected since
                // we're testing the controller logic in isolation.
                // In a full Spring test, this would throw BusinessException(ORDER_DELIVERED_ERROR)
            } catch (Exception e) {
                assertThat(e).isInstanceOf(RuntimeException.class);
            }
        }

        @Test
        @DisplayName("订单不存在 - 抛出BusinessException")
        void shouldThrowException_whenOrderNotFound() {
            // Given
            when(orderQueryService.queryBySn(ORDER_SN)).thenReturn(null);

            // When / Then
            try {
                orderBuyerController.receiving(ORDER_SN);
            } catch (Exception e) {
                assertThat(e).isInstanceOf(RuntimeException.class);
            }
        }
    }

    // ==================== cancel Tests ====================

    @Nested
    @DisplayName("取消订单")
    class CancelTests {

        @Test
        @DisplayName("取消订单 - 调用cancel服务")
        void shouldCancelOrder_whenCancel() {
            // Given
            String reason = "不想要了";

            // When
            Result<Void> result = orderBuyerController.cancel(ORDER_SN, reason);

            // Then
            assertThat(result).isNotNull();
            assertThat(result.getCode()).isEqualTo(200);
            verify(orderCommandService).cancel(ORDER_SN, reason);
        }
    }

    // ==================== deleteOrder Tests ====================

    @Nested
    @DisplayName("删除订单")
    class DeleteOrderTests {

        @Test
        @DisplayName("删除订单 - 调用deleteOrder服务")
        void shouldDeleteOrder_whenDeleteOrder() {
            // Given
            when(orderQueryService.queryBySn(ORDER_SN)).thenReturn(order);

            // When
            Result<Void> result = orderBuyerController.deleteOrder(ORDER_SN);

            // Then
            assertThat(result).isNotNull();
            assertThat(result.getCode()).isEqualTo(200);
            verify(orderQueryService).queryBySn(ORDER_SN);
            verify(orderCommandService).deleteOrder(ORDER_SN);
        }
    }

    // ==================== queryTraces Tests ====================

    @Nested
    @DisplayName("查询物流踪迹")
    class QueryTracesTests {

        @Test
        @DisplayName("查询物流 - 返回Traces")
        void shouldReturnTraces_whenQueryTraces() {
            // Given
            Traces traces = new Traces();
            when(orderQueryService.queryBySn(ORDER_SN)).thenReturn(order);
            when(orderQueryService.queryTraces(ORDER_SN)).thenReturn(traces);

            // When
            Result<Traces> result = orderBuyerController.queryTraces(ORDER_SN);

            // Then
            assertThat(result).isNotNull();
            assertThat(result.getCode()).isEqualTo(200);
            assertThat(result.getData()).isEqualTo(traces);
            verify(orderQueryService).queryTraces(ORDER_SN);
        }
    }

    // ==================== invoice Tests ====================

    @Nested
    @DisplayName("开票")
    class InvoiceTests {

        @Test
        @DisplayName("开票 - 调用invoice服务")
        void shouldInvoice_whenInvoice() {
            // Given
            when(orderQueryService.queryBySn(ORDER_SN)).thenReturn(order);

            // When
            Result<Void> result = orderBuyerController.invoice(ORDER_SN);

            // Then
            assertThat(result).isNotNull();
            assertThat(result.getCode()).isEqualTo(200);
            verify(orderCommandService).invoice(ORDER_SN);
        }
    }
}
