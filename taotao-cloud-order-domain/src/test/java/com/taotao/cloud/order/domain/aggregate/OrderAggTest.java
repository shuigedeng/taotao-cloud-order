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

package com.taotao.cloud.order.domain.aggregate;

import com.taotao.cloud.order.domain.valobj.OrderPrice;
import com.taotao.cloud.order.domain.valobj.OrderStatus;
import com.taotao.cloud.order.domain.valobj.PaymentType;
import com.taotao.cloud.order.domain.valobj.User;
import com.taotao.cloud.order.domain.valobj.detail.OrderDetailType;
import com.taotao.cloud.order.domain.valobj.detail.PlanOrderDetail;
import com.taotao.cloud.order.domain.valobj.detail.Tenant;
import com.taotao.cloud.order.domain.valobj.invoice.InvoiceType;
import com.taotao.cloud.order.domain.valobj.invoice.UploadedFile;
import com.taotao.cloud.order.domain.valobj.plan.PlanType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static com.taotao.cloud.order.domain.valobj.OrderStatus.CREATED;
import static com.taotao.cloud.order.domain.valobj.OrderStatus.PAID;
import static com.taotao.cloud.order.domain.valobj.OrderStatus.REFUNDED;
import static com.taotao.cloud.order.domain.valobj.PaymentType.BANK_TRANSFER;
import static com.taotao.cloud.order.domain.valobj.PaymentType.WX_NATIVE;
import static com.taotao.cloud.order.domain.valobj.PaymentType.WX_TRANSFER;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * OrderAggTest
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
class OrderAggTest {

    private Tenant createDefaultTenant() {
        return new Tenant();
    }

    private User createDefaultUser() {
        return new User();
    }

    private PlanOrderDetail createPlanDetail(PlanType planType, int yearDuration) {
        return PlanOrderDetail.builder()
                .planType(planType)
                .yearDuration(yearDuration)
                .type(OrderDetailType.PLAN)
                .build();
    }

    // ==================== Constructor Tests ====================

    @Nested
    @DisplayName("订单构造器")
    class ConstructorTests {

        @Test
        @DisplayName("创建WX_NATIVE订单 - 状态为CREATED, bankTransferCode为null")
        void shouldCreateWxNativeOrder_whenConstructed() {
            Tenant tenant = createDefaultTenant();
            User user = createDefaultUser();
            PlanOrderDetail detail = createPlanDetail(PlanType.PROFESSIONAL, 2);

            OrderAgg order = new OrderAgg(detail, WX_NATIVE, tenant, user);

            assertThat(order.getStatus()).isEqualTo(CREATED);
            assertThat(order.getPaymentType()).isEqualTo(WX_NATIVE);
            assertThat(order.getPrice()).isNotNull();
            assertThat(order.getBankTransferCode()).isNull();
            assertThat(order.getScreenShots()).isEmpty();
        }

        @Test
        @DisplayName("创建BANK_TRANSFER订单 - 自动生成bankTransferCode")
        void shouldGenerateBankTransferCode_whenBankTransfer() {
            Tenant tenant = createDefaultTenant();
            User user = createDefaultUser();
            PlanOrderDetail detail = createPlanDetail(PlanType.BASIC, 1);

            OrderAgg order = new OrderAgg(detail, BANK_TRANSFER, tenant, user);

            assertThat(order.getStatus()).isEqualTo(CREATED);
            assertThat(order.getPaymentType()).isEqualTo(BANK_TRANSFER);
            assertThat(order.getBankTransferCode()).isNotNull();
            assertThat(order.getBankTransferCode()).hasSize(6);
        }

        @Test
        @DisplayName("价格由OrderDetail.calculatePrice计算得出")
        void shouldSetPriceFromDetail_whenConstructed() {
            Tenant tenant = createDefaultTenant();
            User user = createDefaultUser();
            PlanOrderDetail detail = createPlanDetail(PlanType.PROFESSIONAL, 2);

            OrderAgg order = new OrderAgg(detail, WX_NATIVE, tenant, user);

            assertThat(order.getPrice()).isNotNull();
            assertThat(order.getPrice().getOriginalTotalPrice())
                    .as("专业版2年原价 = 6980 * 2 = 13960.00")
                    .isEqualTo("13960.00");
        }

        @Test
        @DisplayName("planVersion从tenant获取")
        void shouldSetPlanVersionFromTenant_whenConstructed() {
            Tenant tenant = createDefaultTenant();
            User user = createDefaultUser();
            PlanOrderDetail detail = createPlanDetail(PlanType.ADVANCED, 1);

            OrderAgg order = new OrderAgg(detail, WX_NATIVE, tenant, user);

            assertThat(order.getPlanVersion()).isNotNull();
        }
    }

    // ==================== WxPay Tests ====================

    @Nested
    @DisplayName("微信在线支付")
    class WxPayTests {

        @Test
        @DisplayName("首次微信支付 - 状态从CREATED变为PAID")
        void shouldTransitionToPaid_whenFirstWxPay() {
            Tenant tenant = createDefaultTenant();
            User user = createDefaultUser();
            OrderAgg order = new OrderAgg(createPlanDetail(PlanType.PROFESSIONAL, 1), WX_NATIVE, tenant, user);

            Instant paidAt = Instant.now();
            order.wxPay("wxTxn12345", paidAt, user);

            assertThat(order.getStatus()).isEqualTo(PAID);
            assertThat(order.getWxTxnId()).isEqualTo("wxTxn12345");
            assertThat(order.getPaidAt()).isEqualTo(paidAt);
        }

        @Test
        @DisplayName("重复微信支付 - 状态保持PAID,更新txnId")
        void shouldKeepPaid_whenSecondWxPay() {
            Tenant tenant = createDefaultTenant();
            User user = createDefaultUser();
            OrderAgg order = new OrderAgg(createPlanDetail(PlanType.PROFESSIONAL, 1), WX_NATIVE, tenant, user);

            order.wxPay("firstTxn", Instant.now(), user);
            order.wxPay("secondTxn", Instant.now(), user);

            assertThat(order.getStatus()).isEqualTo(PAID);
            assertThat(order.getWxTxnId()).isEqualTo("secondTxn");
        }
    }

    // ==================== WxTransferPay Tests ====================

    @Nested
    @DisplayName("线下微信转账")
    class WxTransferPayTests {

        @Test
        @DisplayName("首次微信转账 - 状态变为PAID, screenshots被设置")
        void shouldTransitionToPaid_whenFirstWxTransferPay() {
            Tenant tenant = createDefaultTenant();
            User user = createDefaultUser();
            // WX_TRANSFER payment type
            PlanOrderDetail detail = createPlanDetail(PlanType.FLAGSHIP, 1);

            // Note: OrderAgg constructor doesn't validate payment type vs detail type
            // Use WX_NATIVE and wxTransferPay still works due to commented-out guard
            OrderAgg order = new OrderAgg(detail, WX_NATIVE, tenant, user);

            Instant paidAt = Instant.now();
            List<UploadedFile> screenshots = List.of(new UploadedFile());
            order.wxTransferPay(screenshots, paidAt, user);

            assertThat(order.getStatus()).isEqualTo(PAID);
            assertThat(order.getScreenShots()).isEqualTo(screenshots);
            assertThat(order.getPaidAt()).isEqualTo(paidAt);
        }
    }

    // ==================== BankTransferPay Tests ====================

    @Nested
    @DisplayName("银行对公转账")
    class BankTransferPayTests {

        @Test
        @DisplayName("BANK_TRANSFER订单转账 - 状态变为PAID")
        void shouldTransitionToPaid_whenBankTransferPay() {
            Tenant tenant = createDefaultTenant();
            User user = createDefaultUser();
            OrderAgg order = new OrderAgg(createPlanDetail(PlanType.BASIC, 1), BANK_TRANSFER, tenant, user);

            Instant paidAt = Instant.now();
            order.bankTransferPay("account123", "工商银行", paidAt, user);

            assertThat(order.getStatus()).isEqualTo(PAID);
            assertThat(order.getBankTransferAccountId()).isEqualTo("account123");
            assertThat(order.getBankName()).isEqualTo("工商银行");
            assertThat(order.getPaidAt()).isEqualTo(paidAt);
        }

        @Test
        @DisplayName("非BANK_TRANSFER订单 - bankTransferPay跳过,状态保持CREATED")
        void shouldSkip_whenNotBankTransferType() {
            Tenant tenant = createDefaultTenant();
            User user = createDefaultUser();
            OrderAgg order = new OrderAgg(createPlanDetail(PlanType.BASIC, 1), WX_NATIVE, tenant, user);

            order.bankTransferPay("account456", "建设银行", Instant.now(), user);

            assertThat(order.getStatus()).isEqualTo(CREATED);
            assertThat(order.getBankTransferAccountId()).isNull();
            assertThat(order.getBankName()).isNull();
        }
    }

    // ==================== Refund Tests ====================

    @Nested
    @DisplayName("退款")
    class RefundTests {

        @Test
        @DisplayName("已支付订单退款 - 状态变为REFUNDED")
        void shouldTransitionToRefunded_whenPaidOrderRefund() {
            Tenant tenant = createDefaultTenant();
            User user = createDefaultUser();
            OrderAgg order = new OrderAgg(createPlanDetail(PlanType.PROFESSIONAL, 1), WX_NATIVE, tenant, user);
            order.wxPay("txn1", Instant.now(), user);

            order.refund("客户主动退款", user);

            assertThat(order.getStatus()).isEqualTo(REFUNDED);
            assertThat(order.getRefundReason()).isEqualTo("客户主动退款");
        }

        @Test
        @DisplayName("未支付订单退款 - 跳过,状态保持CREATED")
        void shouldSkip_whenCreatedOrderRefund() {
            Tenant tenant = createDefaultTenant();
            User user = createDefaultUser();
            OrderAgg order = new OrderAgg(createPlanDetail(PlanType.PROFESSIONAL, 1), WX_NATIVE, tenant, user);

            order.refund("不应该退款", user);

            assertThat(order.getStatus()).isEqualTo(CREATED);
        }
    }

    // ==================== RequestInvoice Tests ====================

    @Nested
    @DisplayName("申请发票")
    class RequestInvoiceTests {

        @Test
        @DisplayName("申请发票 - invoice字段被设置")
        void shouldSetInvoice_whenRequestInvoice() {
            Tenant tenant = createDefaultTenant();
            User user = createDefaultUser();
            OrderAgg order = new OrderAgg(createPlanDetail(PlanType.PROFESSIONAL, 1), WX_NATIVE, tenant, user);

            order.requestInvoice(InvoiceType.VAT_NORMAL, null, "test@example.com", user);

            assertThat(order.isInvoiceRequested()).isTrue();
            assertThat(order.getInvoice()).isNotNull();
            assertThat(order.getInvoice().getEmail()).isEqualTo("test@example.com");
        }

        @Test
        @DisplayName("未申请发票 - isInvoiceRequested返回false")
        void shouldNotHaveInvoice_whenNotRequested() {
            Tenant tenant = createDefaultTenant();
            User user = createDefaultUser();
            OrderAgg order = new OrderAgg(createPlanDetail(PlanType.PROFESSIONAL, 1), WX_NATIVE, tenant, user);

            assertThat(order.isInvoiceRequested()).isFalse();
        }
    }

    // ==================== Description Tests ====================

    @Nested
    @DisplayName("订单描述")
    class DescriptionTests {

        @Test
        @DisplayName("description委托给OrderDetail")
        void shouldDelegateToDetail_whenDescription() {
            Tenant tenant = createDefaultTenant();
            User user = createDefaultUser();
            PlanOrderDetail detail = createPlanDetail(PlanType.FLAGSHIP, 3);
            OrderAgg order = new OrderAgg(detail, WX_NATIVE, tenant, user);

            String desc = order.description();

            assertThat(desc).contains("旗舰版").contains("3年");
        }
    }

    // ==================== Status Check Tests ====================

    @Nested
    @DisplayName("状态判断")
    class StatusCheckTests {

        @Test
        @DisplayName("atCreated - 新建订单返回true")
        void shouldReturnTrue_whenAtCreated() {
            Tenant tenant = createDefaultTenant();
            User user = createDefaultUser();
            OrderAgg order = new OrderAgg(createPlanDetail(PlanType.BASIC, 1), WX_NATIVE, tenant, user);

            assertThat(order.atCreated()).isTrue();
            assertThat(order.atPaid()).isFalse();
        }

        @Test
        @DisplayName("atPaid - 支付后返回true")
        void shouldReturnTrue_whenAtPaid() {
            Tenant tenant = createDefaultTenant();
            User user = createDefaultUser();
            OrderAgg order = new OrderAgg(createPlanDetail(PlanType.BASIC, 1), WX_NATIVE, tenant, user);
            order.wxPay("txn1", Instant.now(), user);

            assertThat(order.atCreated()).isFalse();
            assertThat(order.atPaid()).isTrue();
        }

        @Test
        @DisplayName("退款后 - atCreated和atPaid都返回false")
        void shouldReturnFalse_whenRefunded() {
            Tenant tenant = createDefaultTenant();
            User user = createDefaultUser();
            OrderAgg order = new OrderAgg(createPlanDetail(PlanType.BASIC, 1), WX_NATIVE, tenant, user);
            order.wxPay("txn1", Instant.now(), user);
            order.refund("退款", user);

            assertThat(order.atCreated()).isFalse();
            assertThat(order.atPaid()).isFalse();
        }
    }

    // ==================== Update Delivery Tests ====================

    @Nested
    @DisplayName("更新物流")
    class UpdateDeliveryTests {

        @Test
        @DisplayName("非码牌印刷订单 - 更新物流跳过")
        void shouldSkip_whenNotPlatePrintingType() {
            Tenant tenant = createDefaultTenant();
            User user = createDefaultUser();
            // Plan order, not PLATE_PRINTING
            OrderAgg order = new OrderAgg(createPlanDetail(PlanType.PROFESSIONAL, 1), WX_NATIVE, tenant, user);
            order.wxPay("txn1", Instant.now(), user);

            order.updateDelivery(null, user);

            assertThat(order.getDelivery()).isNull();
        }
    }
}
