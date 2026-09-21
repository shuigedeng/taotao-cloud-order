package com.taotao.cloud.order.infrastructure.adapter;

import com.taotao.cloud.order.application.adapter.OrderConfigPort;
import com.taotao.cloud.order.infrastructure.configuration.properties.PaymentProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class OrderConfigPortImpl implements OrderConfigPort {
    private final PaymentProperties properties;
    
    @Override
    public int getMaxRetryTimes() {
        return properties.getMaxRetryTimes();
    }
    
    @Override
    public BigDecimal getDiscountRate() {
        return properties.getDiscountRate();
    }
}
