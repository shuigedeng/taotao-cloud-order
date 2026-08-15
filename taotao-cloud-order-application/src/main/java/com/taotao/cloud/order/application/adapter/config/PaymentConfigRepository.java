package com.taotao.cloud.order.application.adapter.config;

import java.math.BigDecimal;

public interface PaymentConfigRepository {
    int getMaxRetryTimes();
    BigDecimal getDiscountRate();
}
