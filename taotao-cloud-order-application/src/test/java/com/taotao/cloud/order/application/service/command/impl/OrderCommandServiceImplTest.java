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

import com.taotao.cloud.order.application.dto.order.command.CreateOrderCommand;
import com.taotao.cloud.order.application.dto.order.command.CreateOrderResponse;
import com.taotao.cloud.order.application.dto.order.command.RequestInvoiceCommand;
import com.taotao.cloud.order.domain.aggregate.OrderAgg;
import com.taotao.cloud.order.domain.factory.OrderFactory;
import com.taotao.cloud.order.domain.repository.OrderRepository;
import com.taotao.cloud.order.domain.valobj.OrderPrice;
import com.taotao.cloud.order.domain.valobj.PaymentType;
import com.taotao.cloud.order.domain.valobj.User;
import com.taotao.cloud.order.domain.valobj.detail.OrderDetail;
import com.taotao.cloud.order.domain.valobj.invoice.InvoiceType;
import com.taotao.cloud.order.domain.valobj.invoice.UploadedFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * OrderCommandServiceImplTest
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
@ExtendWith(MockitoExtension.class)
class OrderCommandServiceImplTest {

    @Mock
    private OrderFactory orderFactory;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderCommandServiceImpl orderCommandService;

    @Mock
    private OrderAgg orderAgg;

    @Mock
    private User user;

    @Mock
    private OrderDetail orderDetail;

    @Mock
    private OrderPrice orderPrice;

    @Captor
    private ArgumentCaptor<OrderAgg> orderCaptor;

    private static final String ORDER_ID = "ODR123456789";
    private static final String WX_TXN_ID = "wxTxn20251219001";
    private static final String BANK_ACCOUNT_ID = "bankAccount001";
    private static final String BANK_NAME = "中国银行";

    // ==================== createOrder Tests ====================

    @Nested
    @DisplayName("创建订单")
    class CreateOrderTests {

        @Test
        @DisplayName("调用OrderFactory.createOrder并保存")
        void shouldCreateAndSaveOrder_whenCreateOrder() {
            // Given
            CreateOrderCommand command = CreateOrderCommand.builder()
                    .detail(orderDetail)
                    .paymentType(PaymentType.WX_NATIVE)
                    .build();

            when(orderFactory.createOrder(eq(orderDetail), eq(PaymentType.WX_NATIVE), any(), eq(user)))
                    .thenReturn(orderAgg);
            when(orderAgg.getId()).thenReturn(ORDER_ID);
            when(orderAgg.getPaymentType()).thenReturn(PaymentType.WX_NATIVE);
            when(orderAgg.getWxPayQrUrl()).thenReturn("weixin://wxpay/qr/xyz");
            when(orderAgg.getPrice()).thenReturn(orderPrice);
            when(orderAgg.description()).thenReturn("购买专业版（2年）");

            // When
            CreateOrderResponse response = orderCommandService.createOrder(command, user);

            // Then
            verify(orderFactory).createOrder(eq(orderDetail), eq(PaymentType.WX_NATIVE), any(), eq(user));
            verify(orderRepository).save(orderAgg);

            assertThat(response).isNotNull();
            assertThat(response.getId()).isEqualTo(ORDER_ID);
            assertThat(response.getPaymentType()).isEqualTo(PaymentType.WX_NATIVE);
            assertThat(response.getWxPayQrUrl()).isEqualTo("weixin://wxpay/qr/xyz");
            assertThat(response.getPayDescription()).isEqualTo("购买专业版（2年）");
        }

        @Test
        @DisplayName("BANK_TRANSFER订单 - bankTransferCode不为空")
        void shouldIncludeBankTransferCode_whenBankTransfer() {
            // Given
            CreateOrderCommand command = CreateOrderCommand.builder()
                    .detail(orderDetail)
                    .paymentType(PaymentType.BANK_TRANSFER)
                    .build();

            when(orderFactory.createOrder(eq(orderDetail), eq(PaymentType.BANK_TRANSFER), any(), eq(user)))
                    .thenReturn(orderAgg);
            when(orderAgg.getId()).thenReturn(ORDER_ID);
            when(orderAgg.getPaymentType()).thenReturn(PaymentType.BANK_TRANSFER);
            when(orderAgg.getBankTransferCode()).thenReturn("654321");
            when(orderAgg.getPrice()).thenReturn(orderPrice);
            when(orderAgg.description()).thenReturn("购买基础版（1年）");

            // When
            CreateOrderResponse response = orderCommandService.createOrder(command, user);

            // Then
            assertThat(response.getBankTransferCode()).isEqualTo("654321");
        }
    }

    // ==================== wxPay Tests ====================

    @Nested
    @DisplayName("微信在线支付")
    class WxPayTests {

        @Test
        @DisplayName("找到订单并执行wxPay和save")
        void shouldPayOrder_whenWxPay() {
            // Given
            Instant paidAt = Instant.now();
            when(orderRepository.byIdOptional(ORDER_ID)).thenReturn(Optional.of(orderAgg));
            when(orderAgg.atCreated()).thenReturn(true);

            // When
            orderCommandService.wxPay(ORDER_ID, WX_TXN_ID, paidAt, user);

            // Then
            verify(orderRepository).byIdOptional(ORDER_ID);
            verify(orderAgg).wxPay(WX_TXN_ID, paidAt, user);
            verify(orderRepository).save(orderAgg);
        }

        @Test
        @DisplayName("订单不存在 - 跳过支付")
        void shouldSkip_whenOrderNotFound() {
            // Given
            when(orderRepository.byIdOptional(ORDER_ID)).thenReturn(Optional.empty());

            // When
            orderCommandService.wxPay(ORDER_ID, WX_TXN_ID, Instant.now(), user);

            // Then
            verify(orderAgg, never()).wxPay(any(), any(), any());
            verify(orderRepository, never()).save(any());
        }

        @Test
        @DisplayName("订单已存在 - 即使atCreated为false也执行wxPay并保存")
        void shouldUpdateWxPayInfo_whenOrderAlreadyPaid() {
            // Given
            Instant paidAt = Instant.now();
            when(orderRepository.byIdOptional(ORDER_ID)).thenReturn(Optional.of(orderAgg));
            when(orderAgg.atCreated()).thenReturn(false);

            // When
            orderCommandService.wxPay(ORDER_ID, WX_TXN_ID, paidAt, user);

            // Then
            verify(orderAgg).wxPay(WX_TXN_ID, paidAt, user);
            verify(orderRepository).save(orderAgg);
        }
    }

    // ==================== wxTransferPay Tests ====================

    @Nested
    @DisplayName("线下微信转账")
    class WxTransferPayTests {

        @Test
        @DisplayName("找到订单并执行wxTransferPay和save")
        void shouldPayOrder_whenWxTransferPay() {
            // Given
            Instant paidAt = Instant.now();
            List<UploadedFile> screenShots = List.of(new UploadedFile());
            when(orderRepository.byId(ORDER_ID)).thenReturn(orderAgg);
            when(orderAgg.atCreated()).thenReturn(true);

            // When
            orderCommandService.wxTransferPay(ORDER_ID, screenShots, paidAt, user);

            // Then
            verify(orderRepository).byId(ORDER_ID);
            verify(orderAgg).wxTransferPay(screenShots, paidAt, user);
            verify(orderRepository).save(orderCaptor.capture());
        }
    }

    // ==================== bankTransferPay Tests ====================

    @Nested
    @DisplayName("银行对公转账")
    class BankTransferPayTests {

        @Test
        @DisplayName("找到订单并执行bankTransferPay和save")
        void shouldPayOrder_whenBankTransferPay() {
            // Given
            Instant paidAt = Instant.now();
            when(orderRepository.byId(ORDER_ID)).thenReturn(orderAgg);
            when(orderAgg.atCreated()).thenReturn(true);

            // When
            orderCommandService.bankTransferPay(ORDER_ID, BANK_ACCOUNT_ID, BANK_NAME, paidAt, user);

            // Then
            verify(orderRepository).byId(ORDER_ID);
            verify(orderAgg).bankTransferPay(BANK_ACCOUNT_ID, BANK_NAME, paidAt, user);
            verify(orderRepository).save(orderAgg);
        }

        @Test
        @DisplayName("已有支付信息 - 更新时跳过状态变更但依然保存")
        void shouldUpdateInfo_whenAlreadyPaid() {
            // Given
            Instant paidAt = Instant.now();
            when(orderRepository.byId(ORDER_ID)).thenReturn(orderAgg);
            when(orderAgg.atCreated()).thenReturn(false);

            // When
            orderCommandService.bankTransferPay(ORDER_ID, BANK_ACCOUNT_ID, BANK_NAME, paidAt, user);

            // Then
            verify(orderAgg).bankTransferPay(BANK_ACCOUNT_ID, BANK_NAME, paidAt, user);
            verify(orderRepository).save(orderAgg);
        }
    }

    // ==================== requestInvoice Tests ====================

    @Nested
    @DisplayName("申请发票")
    class RequestInvoiceTests {

        @Test
        @DisplayName("调用order.requestInvoice并保存")
        void shouldRequestInvoice_whenRequestInvoice() {
            // Given
            RequestInvoiceCommand command = RequestInvoiceCommand.builder()
                    .type(InvoiceType.VAT_NORMAL)
                    .email("user@example.com")
                    .build();
            when(orderRepository.byIdAndCheckTenantShip(ORDER_ID, user)).thenReturn(orderAgg);

            // When
            orderCommandService.requestInvoice(ORDER_ID, command, user);

            // Then
            verify(orderRepository).byIdAndCheckTenantShip(ORDER_ID, user);
            verify(orderAgg).requestInvoice(eq(InvoiceType.VAT_NORMAL), any(), eq("user@example.com"), eq(user));
            verify(orderRepository).save(orderAgg);
        }
    }

    // ==================== delete Tests ====================

    @Nested
    @DisplayName("删除订单")
    class DeleteTests {

        @Test
        @DisplayName("找到订单并删除")
        void shouldDeleteOrder_whenDelete() {
            // Given
            when(orderRepository.byId(ORDER_ID)).thenReturn(orderAgg);

            // When
            orderCommandService.delete(ORDER_ID);

            // Then
            verify(orderRepository).byId(ORDER_ID);
            verify(orderRepository).delete(orderAgg);
        }
    }

    // ==================== issueInvoice Tests ====================

    @Nested
    @DisplayName("开具发票")
    class IssueInvoiceTests {

        @Test
        @DisplayName("调用order.issueInvoice并保存")
        void shouldIssueInvoice_whenIssueInvoice() {
            // Given
            List<UploadedFile> files = List.of(new UploadedFile());
            when(orderRepository.byId(ORDER_ID)).thenReturn(orderAgg);

            // When
            orderCommandService.issueInvoice(ORDER_ID, files, user);

            // Then
            verify(orderAgg).issueInvoice(files, user);
            verify(orderRepository).save(orderAgg);
        }
    }

    // ==================== updateDelivery Tests ====================

    @Nested
    @DisplayName("更新物流")
    class UpdateDeliveryTests {

        @Test
        @DisplayName("调用order.updateDelivery并保存")
        void shouldUpdateDelivery_whenUpdateDelivery() {
            // Given
            when(orderRepository.byId(ORDER_ID)).thenReturn(orderAgg);

            // When
            orderCommandService.updateDelivery(ORDER_ID, null, user);

            // Then
            verify(orderAgg).updateDelivery(null, user);
            verify(orderRepository).save(orderAgg);
        }
    }

    // ==================== refund Tests ====================

    @Nested
    @DisplayName("退款")
    class RefundTests {

        @Test
        @DisplayName("调用order.refund并保存")
        void shouldRefund_whenRefund() {
            // Given
            String reason = "产品质量问题";
            when(orderRepository.byId(ORDER_ID)).thenReturn(orderAgg);

            // When
            orderCommandService.refund(ORDER_ID, reason, user);

            // Then
            verify(orderAgg).refund(reason, user);
            verify(orderRepository).save(orderAgg);
        }
    }
}
