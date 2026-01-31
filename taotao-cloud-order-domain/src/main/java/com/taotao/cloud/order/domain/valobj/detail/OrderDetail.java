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

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.taotao.cloud.order.domain.valobj.OrderPrice;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static lombok.AccessLevel.PROTECTED;

/**
 * OrderDetail
 *
 * @author shuigedeng
 * @version 2026.03
 * @since 2025-12-19 09:30:45
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true)
@JsonSubTypes(
        value = {
                @JsonSubTypes.Type(value = PlanOrderDetail.class, name = "PLAN"),
                @JsonSubTypes.Type(value = ExtraMemberOrderDetail.class, name = "EXTRA_MEMBER"),
                @JsonSubTypes.Type(value = ExtraSmsOrderDetail.class, name = "EXTRA_SMS"),
                @JsonSubTypes.Type(value = ExtraStorageOrderDetail.class, name = "EXTRA_STORAGE"),
                @JsonSubTypes.Type(
                        value = ExtraVideoTrafficOrderDetail.class,
                        name = "EXTRA_VIDEO_TRAFFIC"),
                @JsonSubTypes.Type(value = PlatePrintingOrderDetail.class, name = "PLATE_PRINTING"),
        })
@Getter
@SuperBuilder
@EqualsAndHashCode
@NoArgsConstructor(access = PROTECTED)
public abstract class OrderDetail {

    @NotNull
    private OrderDetailType type;

    public abstract String description();

    public abstract void validate( Tenant tenant );

    protected void validateRequireNonFreePlan( Tenant tenant ) {
        if (tenant.isEffectiveFreePlan()) {
            //            throw new MryException(ORDER_REQUIRE_NON_FREE_PLAN,
            //                    "您当前有效套餐为免费版，无法购买，请升级到付费版套餐后再进行购买。",
            //                    mapOf("orderDetailType", getType()));
        }
    }

    protected abstract OrderPrice doCalculatePrice( Tenant tenant );

    public OrderPrice calculatePrice( Tenant tenant ) {
        OrderPrice price = doCalculatePrice(tenant);
        if (tenant.isMryManageTenant() || tenant.isMryTestingTenant()) {
            return OrderPrice.builder()
                    .originalUpgradePrice(price.getOriginalUpgradePrice())
                    .originalRenewalPrice(price.getOriginalRenewalPrice())
                    .originalTotalPrice(price.getOriginalTotalPrice())
                    .deliveryFee(price.getDeliveryFee())
                    .discount(price.getDiscount())
                    .discountOffsetPrice(price.getDiscountOffsetPrice())
                    .discountedTotalPrice("0.01")
                    .build();
        }
        return price;
    }
}
