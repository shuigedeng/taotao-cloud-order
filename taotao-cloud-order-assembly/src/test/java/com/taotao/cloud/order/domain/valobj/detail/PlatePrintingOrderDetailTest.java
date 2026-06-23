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

package com.taotao.cloud.order.domain.valobj.detail;

import com.taotao.cloud.order.domain.valobj.OrderPrice;
import com.taotao.cloud.order.domain.valobj.delivery.Address;
import com.taotao.cloud.order.domain.valobj.delivery.Consignee;
import com.taotao.cloud.order.domain.valobj.invoice.UploadedFile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * PlatePrintingOrderDetailTest
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
class PlatePrintingOrderDetailTest {

    private static final Consignee DEFAULT_CONSIGNEE = new Consignee(
            "c001",
            "测试用户",
            "13800138000",
            new Address("广东省", "深圳市", "南山区", "科技园南区A栋")
    );

    private Tenant createDefaultTenant() {
        return new Tenant();
    }

    private PlatePrintingOrderDetail createDetail(PlatePrintingType plateType, int amount) {
        return PlatePrintingOrderDetail.builder()
                .plateType(plateType)
                .amount(amount)
                .files(List.of(new UploadedFile()))
                .consignee(DEFAULT_CONSIGNEE)
                .type(OrderDetailType.PLATE_PRINTING)
                .build();
    }

    // ==================== Description Tests ====================

    @Nested
    @DisplayName("订单描述")
    class DescriptionTests {

        @Test
        @DisplayName("描述包含数量、材质和尺寸")
        void shouldContainAmountAndMaterialAndSize_whenDescription() {
            PlatePrintingOrderDetail detail = createDetail(
                    PlatePrintingType.TRANSPARENT_ACRYLIC_60x60, 50);

            String desc = detail.description();

            assertThat(desc).contains("50").contains("透明亚克力").contains("60*60mm");
        }

        @ParameterizedTest
        @DisplayName("不同码牌类型的描述")
        @CsvSource({
                "PVC_CARD_50x50, 100, PVC卡, 50*50mm",
                "PP_ADHESIVE_70x50, 200, PVC不干胶, 70*50mm",
                "ARGENTOUS_ADHESIVE_100x70, 10, 亚银不干胶, 100*70mm"
        })
        void shouldReturnCorrectDescription_forDifferentPlateTypes(
                PlatePrintingType plateType, int amount,
                String expectedMaterial, String expectedSize) {
            PlatePrintingOrderDetail detail = createDetail(plateType, amount);

            String desc = detail.description();

            assertThat(desc).contains(String.valueOf(amount))
                    .contains(expectedMaterial)
                    .contains(expectedSize);
        }
    }

    // ==================== Price Calculation Tests ====================

    @Nested
    @DisplayName("价格计算")
    class PriceCalculationTests {

        @Test
        @DisplayName("透明亚克力50x40, 100个 - 单价3.0, 总价300, 运费0")
        void shouldCalculatePrice_whenBasicAcrylic() {
            Tenant tenant = createDefaultTenant();
            PlatePrintingOrderDetail detail = createDetail(
                    PlatePrintingType.TRANSPARENT_ACRYLIC_50x40, 100);

            OrderPrice price = detail.doCalculatePrice(tenant);

            assertThat(price.getOriginalTotalPrice()).isEqualTo("300.00");
            assertThat(price.getDeliveryFee()).isEqualTo("0.00");
            assertThat(price.getDiscountedTotalPrice()).isEqualTo("300.00");
            assertThat(price.getOriginalUpgradePrice()).isNull();
            assertThat(price.getOriginalRenewalPrice()).isNull();
        }

        @Test
        @DisplayName("PVC卡70x50, 500个 - 单价3.0, 总价1500, 运费0")
        void shouldCalculatePrice_whenPvcCard() {
            Tenant tenant = createDefaultTenant();
            PlatePrintingOrderDetail detail = createDetail(
                    PlatePrintingType.PVC_CARD_70x50, 500);

            OrderPrice price = detail.doCalculatePrice(tenant);

            assertThat(price.getOriginalTotalPrice()).isEqualTo("1500.00");
            assertThat(price.getDiscountedTotalPrice()).isEqualTo("1500.00");
        }

        @ParameterizedTest
        @DisplayName("数量与单价的乘法计算")
        @CsvSource({
                "TRANSPARENT_ACRYLIC_60x40, 10, 30.00",
                "TRANSPARENT_ACRYLIC_100x70, 50, 250.00",
                "PVC_CARD_100x70, 1000, 5000.00",
                "PP_ADHESIVE_50x40, 10000, 7000.00"
        })
        void shouldMultiplyAmountByUnitPrice(
                PlatePrintingType plateType, int amount, String expectedTotal) {
            Tenant tenant = createDefaultTenant();
            PlatePrintingOrderDetail detail = createDetail(plateType, amount);

            OrderPrice price = detail.doCalculatePrice(tenant);

            assertThat(price.getOriginalTotalPrice()).isEqualTo(expectedTotal);
        }

        @Test
        @DisplayName("码牌印刷 - 类型为PLATE_PRINTING")
        void shouldHavePlatePrintingType() {
            PlatePrintingOrderDetail detail = createDetail(
                    PlatePrintingType.PORCELAIN_ACRYLIC_60x60, 30);

            assertThat(detail.getType()).isEqualTo(OrderDetailType.PLATE_PRINTING);
        }
    }

    // ==================== calculatePrice with tenant checks ====================

    @Nested
    @DisplayName("calculatePrice（含tenant校验）")
    class CalculatePriceTests {

        @Test
        @DisplayName("普通租户 - calculatePrice与doCalculatePrice一致")
        void shouldMatchDoCalculatePrice_whenNormalTenant() {
            Tenant tenant = createDefaultTenant();
            PlatePrintingOrderDetail detail = createDetail(
                    PlatePrintingType.TRANSPARENT_ACRYLIC_80x60, 200);

            OrderPrice calculated = detail.calculatePrice(tenant);
            OrderPrice doCalculated = detail.doCalculatePrice(tenant);

            assertThat(calculated.getOriginalTotalPrice())
                    .isEqualTo(doCalculated.getOriginalTotalPrice());
            assertThat(calculated.getDiscountedTotalPrice())
                    .isEqualTo(doCalculated.getDiscountedTotalPrice());
        }
    }
}
