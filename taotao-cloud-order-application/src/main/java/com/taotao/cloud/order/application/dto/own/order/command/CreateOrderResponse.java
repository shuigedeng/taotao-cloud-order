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

package com.taotao.cloud.order.application.dto.own.order.command;

import static lombok.AccessLevel.PRIVATE;

import com.taotao.cloud.order.domain.order.valobj.OrderPrice;
import com.taotao.cloud.order.domain.order.valobj.PaymentType;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class CreateOrderResponse {
    private final Long id;
    private final PaymentType paymentType;
    private final String wxPayQrUrl;
    private final String bankTransferCode;
    private final OrderPrice price;
    private final String payDescription;
    private final Instant createdAt;
}
