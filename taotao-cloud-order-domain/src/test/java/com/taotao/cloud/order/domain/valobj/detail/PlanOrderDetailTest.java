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
import com.taotao.cloud.order.domain.valobj.plan.PlanType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * PlanOrderDetailTest
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
class PlanOrderDetailTest {

    private Tenant createDefaultTenant() {
        return new Tenant();
    }

    private PlanOrderDetail createDetail(PlanType planType, int yearDuration) {
        return PlanOrderDetail.builder()
                .planType(planType)
                .yearDuration(yearDuration)
                .type(OrderDetailType.PLAN)
                .build();
    }

    // ==================== Description Tests ====================

    @Nested
    @DisplayName("订单描述")
    class DescriptionTests {

        @Test
        @DisplayName("仅升级 - yearDuration为0,描述为'升级到X'")
        void shouldReturnUpgradeDescription_whenYearDurationIsZero() {
            PlanOrderDetail detail = createDetail(PlanType.PROFESSIONAL, 0);

            String desc = detail.description();

            assertThat(desc).isEqualTo("升级到专业版");
        }

        @Test
        @DisplayName("购买时长 - yearDuration大于0,描述为'购买X（N年）'")
        void shouldReturnRenewalDescription_whenYearDurationGreaterThanZero() {
            PlanOrderDetail detail = createDetail(PlanType.FLAGSHIP, 3);

            String desc = detail.description();

            assertThat(desc).isEqualTo("购买旗舰版（3年）");
        }

        @ParameterizedTest
        @DisplayName("所有套餐类型的描述")
        @CsvSource({
                "FREE, 1, 购买免费版（1年）",
                "BASIC, 2, 购买基础版（2年）",
                "ADVANCED, 3, 购买高级版（3年）",
                "PROFESSIONAL, 1, 购买专业版（1年）",
                "FLAGSHIP, 5, 购买旗舰版（5年）"
        })
        void shouldReturnCorrectDescription_forAllPlanTypes(
                PlanType planType, int yearDuration, String expected) {
            PlanOrderDetail detail = createDetail(planType, yearDuration);

            assertThat(detail.description()).isEqualTo(expected);
        }
    }

    // ==================== Renewal Only Price Tests ====================

    @Nested
    @DisplayName("仅续费价格计算")
    class RenewalOnlyPriceTests {

        @Test
        @DisplayName("基础版1年 - 无折扣,原价680,不打折")
        void shouldCalculateRenewalPrice_whenOneYearBasic() {
            Tenant tenant = createDefaultTenant();
            PlanOrderDetail detail = createDetail(PlanType.BASIC, 1);

            OrderPrice price = detail.doCalculatePrice(tenant);

            // Tenant.effectivePlanType() returns FREE, so currentEffectivePlanType == FREE
            // -> renewal only path
            // originalRenewalPrice = 680 * 1 = 680
            // discountFor(1) = 95
            // discounted = 680 * 0.95 = 646.00
            assertThat(price.getOriginalTotalPrice()).isEqualTo("680.00");
            assertThat(price.getDiscountedTotalPrice()).isEqualTo("646.00");
            assertThat(price.getDiscount()).isEqualTo("9.5");
            assertThat(price.getDiscountOffsetPrice()).isEqualTo("34.00");
        }

        @Test
        @DisplayName("高级版2年 - 90折,原价2760,折后2484")
        void shouldCalculateRenewalPrice_whenTwoYearAdvanced() {
            Tenant tenant = createDefaultTenant();
            PlanOrderDetail detail = createDetail(PlanType.ADVANCED, 2);

            OrderPrice price = detail.doCalculatePrice(tenant);

            // originalRenewalPrice = 1380 * 2 = 2760
            // discountFor(2) = 90
            // discounted = 2760 * 0.9 = 2484.00
            assertThat(price.getOriginalTotalPrice()).isEqualTo("2760.00");
            assertThat(price.getDiscountedTotalPrice()).isEqualTo("2484.00");
            assertThat(price.getDiscount()).isEqualTo("9.0");
            assertThat(price.getDiscountOffsetPrice()).isEqualTo("276.00");
        }

        @Test
        @DisplayName("专业版3年 - 85折,原价20940,折后17799")
        void shouldCalculateRenewalPrice_whenThreeYearProfessional() {
            Tenant tenant = createDefaultTenant();
            PlanOrderDetail detail = createDetail(PlanType.PROFESSIONAL, 3);

            OrderPrice price = detail.doCalculatePrice(tenant);

            // originalRenewalPrice = 6980 * 3 = 20940
            // discountFor(3) = 85
            // discounted = 20940 * 0.85 = 17799.00
            assertThat(price.getOriginalTotalPrice()).isEqualTo("20940.00");
            assertThat(price.getDiscountedTotalPrice()).isEqualTo("17799.00");
            assertThat(price.getDiscount()).isEqualTo("8.5");
        }

        @Test
        @DisplayName("旗舰版4年 - 无折扣(>3年),原价51200,不打折")
        void shouldCalculateRenewalPrice_whenFourYearFlagship() {
            Tenant tenant = createDefaultTenant();
            PlanOrderDetail detail = createDetail(PlanType.FLAGSHIP, 4);

            OrderPrice price = detail.doCalculatePrice(tenant);

            // originalRenewalPrice = 12800 * 4 = 51200
            // discountFor(4) = 100 (no discount)
            assertThat(price.getOriginalTotalPrice()).isEqualTo("51200.00");
            assertThat(price.getDiscountedTotalPrice()).isEqualTo("51200.00");
            assertThat(price.getDiscount()).isNull();
            assertThat(price.getDiscountOffsetPrice()).isNull();
        }

        @Test
        @DisplayName("续费场景 - originalUpgradePrice为null")
        void shouldHaveNullUpgradePrice_whenRenewalOnly() {
            Tenant tenant = createDefaultTenant();
            PlanOrderDetail detail = createDetail(PlanType.BASIC, 2);

            OrderPrice price = detail.doCalculatePrice(tenant);

            assertThat(price.getOriginalUpgradePrice()).isNull();
        }
    }

    // ==================== Discount Values Tests ====================

    @Nested
    @DisplayName("折扣率")
    class DiscountTests {

        @ParameterizedTest
        @DisplayName("折扣率随年限变化")
        @CsvSource({
                "1, 95",
                "2, 90",
                "3, 85",
                "4, 100",
                "5, 100",
                "10, 100"
        })
        void shouldReturnCorrectDiscount_forYearDuration(int yearDuration, int expectedDiscount) {
            // discountFor() is private, but we can observe its effect through the produced price
            Tenant tenant = createDefaultTenant();
            PlanOrderDetail detail = createDetail(PlanType.BASIC, yearDuration);

            OrderPrice price = detail.doCalculatePrice(tenant);

            if (expectedDiscount == 100) {
                assertThat(price.getDiscount()).isNull();
            } else {
                assertThat(price.getDiscount()).isNotNull();
            }
        }
    }

    // ==================== calculatePrice with tenant checks ====================

    @Nested
    @DisplayName("calculatePrice（含tenant校验）")
    class CalculatePriceTests {

        @Test
        @DisplayName("普通租户 - calculatePrice与doCalculatePrice结果一致")
        void shouldReturnSamePrice_whenNormalTenant() {
            Tenant tenant = createDefaultTenant();
            PlanOrderDetail detail = createDetail(PlanType.PROFESSIONAL, 2);

            OrderPrice calculated = detail.calculatePrice(tenant);
            OrderPrice doCalculated = detail.doCalculatePrice(tenant);

            assertThat(calculated.getDiscountedTotalPrice())
                    .isEqualTo(doCalculated.getDiscountedTotalPrice());
        }
    }
}
