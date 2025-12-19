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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.TypeAlias;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static com.taotao.cloud.order.domain.valobj.plan.PlanType.FREE;
import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;
import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.DAYS;
import static lombok.AccessLevel.PRIVATE;

/**
 * PlanOrderDetail
 *
 * @author shuigedeng
 * @version 2026.01
 * @since 2025-12-19 09:30:45
 */
@Getter
@SuperBuilder
@TypeAlias("PLAN")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = PRIVATE)
public class PlanOrderDetail extends OrderDetail {

    @NotNull
    private PlanType planType;

    @Max(10)
    @Min(0)
    private int yearDuration;

    @Override
    public String description() {
        if (yearDuration == 0) {
            return "升级到" + planType.getName();
        }

        return "购买" + planType.getName() + "（" + yearDuration + "年）";
    }

    @Override
    public void validate( Tenant tenant ) {
        tenant.validateAddPlanDuration(yearDuration);
    }

    @Override
    public OrderPrice doCalculatePrice( Tenant tenant ) {
        PlanType currentEffectivePlanType = tenant.effectivePlanType();
        validate(currentEffectivePlanType, this.planType, this.yearDuration);

        // 仅购买时长
        if (currentEffectivePlanType == FREE || currentEffectivePlanType == this.planType) {
            return calculateRenewalOnlyPrice(this.planType, this.yearDuration);
        }

        // 仅升级
        if (this.yearDuration == 0) {
            return calculateUpgradeOnlyPrice(tenant, currentEffectivePlanType, this.planType);
        }

        // 既升级，又购买时长
        return calculateUpdateAndRenewalPrice(
                tenant, currentEffectivePlanType, this.planType, this.yearDuration);
    }

    private void validate(
            PlanType currentEffectivePlanType, PlanType requestedPlanType, int yearDuration ) {
        //		if (requestedPlanType == FREE) {
        //			throw new MryException(PURCHASE_FREE_PLAN_NOT_ALLOWED,
        //				"免费版套餐无法购买。", mapOf("planType", requestedPlanType));
        //		}
        //
        //		if (currentEffectivePlanType == FREE && yearDuration == 0) {
        //			throw new MryException(UPGRADE_FREE_PLAN_NOT_ALLOWED,
        //				"当前有效套餐为免费版，不支持仅升级，请提供购买时长。", mapOf("planType", requestedPlanType));
        //		}
        //
        //		if (currentEffectivePlanType == requestedPlanType && yearDuration == 0) {
        //			throw new MryException(UPGRADE_TO_SAME_PLAN_NOT_ALLOWED,
        //				"不支持仅升级相同版本套餐，请提供购买时长。", mapOf("planType", requestedPlanType));
        //		}
        //
        //		if (!requestedPlanType.covers(currentEffectivePlanType)) {
        //			throw new MryException(DOWNGRADE_PLAN_NOT_ALLOWED,
        //				"购买套餐级别不能低于当前套餐级别。", mapOf("planType", requestedPlanType));
        //		}
    }

    private OrderPrice calculateRenewalOnlyPrice( PlanType requestedPlanType, int yearDuration ) {
        BigDecimal originalTotalPrice = originalRenewalPrice(requestedPlanType, yearDuration);
        String originalTotalPriceString = originalTotalPrice.setScale(2, HALF_UP).toString();

        int discount = discountFor(yearDuration);
        if (discount == 100) {
            return OrderPrice.builder()
                    .originalUpgradePrice(null)
                    .originalRenewalPrice(originalTotalPriceString)
                    .originalTotalPrice(originalTotalPriceString)
                    .discount(null)
                    .discountOffsetPrice(null)
                    .discountedTotalPrice(originalTotalPriceString)
                    .build();
        }

        BigDecimal discountedTotalPrice = originalTotalPrice.multiply(valueOf(discount / 100.0));
        BigDecimal discountOffsetPrice = originalTotalPrice.subtract(discountedTotalPrice);
        return OrderPrice.builder()
                .originalUpgradePrice(null)
                .originalRenewalPrice(originalTotalPriceString)
                .originalTotalPrice(originalTotalPriceString)
                .discount(valueOf(discount / 10.0).setScale(1, HALF_UP).toString())
                .discountOffsetPrice(discountOffsetPrice.setScale(2, HALF_UP).toString())
                .discountedTotalPrice(discountedTotalPrice.setScale(2, HALF_UP).toString())
                .build();
    }

    private OrderPrice calculateUpgradeOnlyPrice(
            Tenant tenant, PlanType currentEffectivePlanType, PlanType requestedPlanType ) {
        BigDecimal originalUpgradePrice =
                originalUpgradePrice(tenant, currentEffectivePlanType, requestedPlanType);

        if (originalUpgradePrice.compareTo(ZERO) == 0) {
            return OrderPrice.builder()
                    .originalUpgradePrice("0.01")
                    .originalRenewalPrice(null)
                    .originalTotalPrice("0.01")
                    .discount(null)
                    .discountOffsetPrice(null)
                    .discountedTotalPrice("0.01")
                    .build();
        }

        String originalTotalPriceString = originalUpgradePrice.setScale(2, HALF_UP).toString();
        return OrderPrice.builder()
                .originalUpgradePrice(originalTotalPriceString)
                .originalRenewalPrice(null)
                .originalTotalPrice(originalTotalPriceString)
                .discount(null)
                .discountOffsetPrice(null)
                .discountedTotalPrice(originalTotalPriceString)
                .build();
    }

    private OrderPrice calculateUpdateAndRenewalPrice(
            Tenant tenant,
            PlanType currentEffectivePlanType,
            PlanType requestedPlanType,
            int yearDuration ) {
        BigDecimal originalRenewalPrice = originalRenewalPrice(requestedPlanType, yearDuration);
        BigDecimal originalUpgradePrice =
                originalUpgradePrice(tenant, currentEffectivePlanType, requestedPlanType);
        BigDecimal originalTotalPrice = originalRenewalPrice.add(originalUpgradePrice);
        String originalTotalPriceString = originalTotalPrice.setScale(2, HALF_UP).toString();

        int discount = discountFor(yearDuration);
        if (discount == 100) {
            return OrderPrice.builder()
                    .originalUpgradePrice(originalUpgradePrice.setScale(2, HALF_UP).toString())
                    .originalRenewalPrice(originalRenewalPrice.setScale(2, HALF_UP).toString())
                    .originalTotalPrice(originalTotalPriceString)
                    .discount(null)
                    .discountOffsetPrice(null)
                    .discountedTotalPrice(originalTotalPriceString)
                    .build();
        }

        BigDecimal discountedTotalPrice = originalTotalPrice.multiply(valueOf(discount / 100.0));
        BigDecimal discountOffsetPrice = originalTotalPrice.subtract(discountedTotalPrice);

        return OrderPrice.builder()
                .originalUpgradePrice(originalUpgradePrice.setScale(2, HALF_UP).toString())
                .originalRenewalPrice(originalRenewalPrice.setScale(2, HALF_UP).toString())
                .originalTotalPrice(originalTotalPriceString)
                .discount(valueOf(discount / 10.0).setScale(1, HALF_UP).toString())
                .discountOffsetPrice(discountOffsetPrice.setScale(2, HALF_UP).toString())
                .discountedTotalPrice(discountedTotalPrice.setScale(2, HALF_UP).toString())
                .build();
    }

    private int discountFor( int yearDuration ) {
        if (yearDuration == 1) {
            return 95;
        }

        if (yearDuration == 2) {
            return 90;
        }

        if (yearDuration == 3) {
            return 85;
        }

        return 100;
    }

    private BigDecimal originalRenewalPrice( PlanType requestedPlanType, int yearDuration ) {
        return valueOf(requestedPlanType.getPrice()).multiply(valueOf(yearDuration));
    }

    private BigDecimal originalUpgradePrice(
            Tenant tenant, PlanType currentEffectivePlanType, PlanType requestedPlanType ) {
        Instant packagesExpiredAt = tenant.packagesExpiredAt();
        long leftDays = DAYS.between(now(), packagesExpiredAt);
        if (leftDays < 0) {
            // shouldn't reach here
            throw new RuntimeException("Left days cannot be less than 0.");
        }

        double ratio = leftDays / 365.0;
        BigDecimal subtract =
                valueOf(requestedPlanType.getPrice())
                        .subtract(valueOf(currentEffectivePlanType.getPrice()));
        return subtract.multiply(valueOf(ratio));
    }
}
