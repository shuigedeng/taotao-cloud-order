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
import com.taotao.cloud.order.domain.valobj.delivery.Consignee;
import com.taotao.cloud.order.domain.valobj.invoice.UploadedFile;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.TypeAlias;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;
import static lombok.AccessLevel.PRIVATE;

@Getter
@SuperBuilder
@TypeAlias("PLATE_PRINTING")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = PRIVATE)
public class PlatePrintingOrderDetail extends OrderDetail {

    @NotNull private PlatePrintingType plateType;

    @Min(10)
    @Max(10000)
    private int amount;

    @Valid
    @NotNull
    @NotEmpty
    @Size(max = 10)
    private List<UploadedFile> files;

    @Valid @NotNull private Consignee consignee;

    @Override
    public String description() {
        return "印刷码牌" + amount + "个（" + plateType.description() + "）";
    }

    @Override
    public void validate(Tenant tenant) {}

    @Override
    public OrderPrice doCalculatePrice(Tenant tenant) {
        BigDecimal originalPrice =
                valueOf(this.amount).multiply(valueOf(this.plateType.getUnitPrice()));
        BigDecimal deliveryFee = valueOf(this.plateType.getDeliveryFee());

        return OrderPrice.builder()
                .originalUpgradePrice(null)
                .originalRenewalPrice(null)
                .originalTotalPrice(originalPrice.setScale(2, HALF_UP).toString())
                .deliveryFee(deliveryFee.setScale(2, HALF_UP).toString())
                .discount(null)
                .discountOffsetPrice(null)
                .discountedTotalPrice(
                        originalPrice.add(deliveryFee).setScale(2, HALF_UP).toString())
                .build();
    }
}
