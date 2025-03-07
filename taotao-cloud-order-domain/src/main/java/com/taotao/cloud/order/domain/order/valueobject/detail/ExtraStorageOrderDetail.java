package com.taotao.cloud.order.domain.order.valueobject.detail;

import com.taotao.cloud.order.domain.order.valueobject.OrderPrice;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.TypeAlias;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;
import static lombok.AccessLevel.PRIVATE;

@Getter
@SuperBuilder
@TypeAlias("EXTRA_STORAGE")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = PRIVATE)
public class ExtraStorageOrderDetail extends OrderDetail {
    private static final int PRICE_PER_G = 30;

    @Min(10)
    @Max(10000)
    private int amount;

    @Override
    public String description() {
        return "增购存储空间" + amount + "G";
    }

    @Override
    public void validate(Tenant tenant) {
        validateRequireNonFreePlan(tenant);
        tenant.validateAddExtraStorage(amount);
    }

    @Override
    public OrderPrice doCalculatePrice(Tenant tenant) {
        String priceString = valueOf(this.amount)
                .multiply(valueOf(PRICE_PER_G))
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
