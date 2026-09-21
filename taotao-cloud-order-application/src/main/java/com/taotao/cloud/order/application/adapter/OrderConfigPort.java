package com.taotao.cloud.order.application.adapter;

import java.math.BigDecimal;

public interface OrderConfigPort {
    int getMaxRetryTimes();
    BigDecimal getDiscountRate();
}
