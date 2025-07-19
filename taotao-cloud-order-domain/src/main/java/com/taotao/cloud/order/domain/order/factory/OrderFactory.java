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
    public Order createOrder(
            OrderDetail detail, PaymentType paymentType, Tenant tenant, User user) {
        return null;
    }
    //    private final WxPayService wxPayService;
    //
    //    public Order createOrder(OrderDetail detail, PaymentType paymentType, Tenant tenant, User
    // user) {
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
