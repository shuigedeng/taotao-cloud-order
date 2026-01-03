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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.TypeAlias;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;
import static lombok.AccessLevel.PRIVATE;

/**
 * ExtraMemberOrderDetail
 *
 * @author shuigedeng
 * @version 2026.02
 * @since 2025-12-19 09:30:45
 */
@Getter
@SuperBuilder
@TypeAlias("EXTRA_MEMBER")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = PRIVATE)
public class ExtraMemberOrderDetail extends OrderDetail {

    private static final int PRICE_PER_MEMBER = 200;

    @Min(1)
    @Max(5000)
    private int amount;

    @Override
    public String description() {
        return "增购成员" + amount + "名";
    }

    @Override
    public void validate( Tenant tenant ) {
        validateRequireNonFreePlan(tenant);
        tenant.validateAddExtraMembers(amount);
    }

    @Override
    public OrderPrice doCalculatePrice( Tenant tenant ) {
        String priceString =
                valueOf(this.amount)
                        .multiply(valueOf(PRICE_PER_MEMBER))
                        .setScale(2, HALF_UP)
                        .toString();

        return OrderPrice.builder()
                .originalUpgradePrice(null)
                .originalRenewalPrice(null)
                .originalTotalPrice(priceString)
                .discount(null)
                .discountOffsetPrice(null)
                .discountedTotalPrice(priceString)
                .build();
    }
}
