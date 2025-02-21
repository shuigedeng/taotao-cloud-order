package com.taotao.cloud.order.domain.order.valueobject.detail;

import com.taotao.cloud.order.domain.order.valueobject.OrderPrice;
import com.taotao.cloud.order.domain.order.valueobject.delivery.Consignee;
import com.taotao.cloud.order.domain.order.valueobject.invoice.UploadedFile;
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

    @NotNull
    private PlatePrintingType plateType;

    @Min(10)
    @Max(10000)
    private int amount;

    @Valid
    @NotNull
    @NotEmpty
    @Size(max = 10)
    private List<UploadedFile> files;

    @Valid
    @NotNull
    private Consignee consignee;

    @Override
    public String description() {
        return "印刷码牌" + amount + "个（" + plateType.description() + "）";
    }

    @Override
    public void validate(Tenant tenant) {

    }

    @Override
    public OrderPrice doCalculatePrice(Tenant tenant) {
        BigDecimal originalPrice = valueOf(this.amount).multiply(valueOf(this.plateType.getUnitPrice()));
        BigDecimal deliveryFee = valueOf(this.plateType.getDeliveryFee());

        return OrderPrice.builder()
                .originalUpgradePrice(null)
                .originalRenewalPrice(null)
                .originalTotalPrice(originalPrice.setScale(2, HALF_UP).toString())
                .deliveryFee(deliveryFee.setScale(2, HALF_UP).toString())
                .discount(null)
                .discountOffsetPrice(null)
                .discountedTotalPrice(originalPrice.add(deliveryFee).setScale(2, HALF_UP).toString())
                .build();
    }
}
