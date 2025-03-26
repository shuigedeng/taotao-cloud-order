package com.taotao.cloud.order.domain.order.factory;

import com.taotao.cloud.order.domain.order.aggregate.Order;
import com.taotao.cloud.order.domain.order.valueobject.PaymentType;
import com.taotao.cloud.order.domain.order.valueobject.User;
import com.taotao.cloud.order.domain.order.valueobject.detail.OrderDetail;
import com.taotao.cloud.order.domain.order.valueobject.detail.Tenant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class OrderFactory {
	public Order createOrder(OrderDetail detail, PaymentType paymentType, Tenant tenant, User user) {
		return null;
	}
//    private final WxPayService wxPayService;
//
//    public Order createOrder(OrderDetail detail, PaymentType paymentType, Tenant tenant, User user) {
//        Order order = new Order(detail, paymentType, tenant, user);
//
//        if (paymentType == WX_NATIVE) {
//            String wxPayOrder = wxPayService.createWxPayOrder(order);
//            order.setWxPayQrUrl(wxPayOrder);
//        }
//
//        return order;
//    }
}
