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

package com.taotao.cloud.order.application.dto.own.order.query;

import static lombok.AccessLevel.PRIVATE;

import com.taotao.cloud.order.domain.order.valobj.OrderStatus;
import com.taotao.cloud.order.domain.order.valobj.detail.OrderDetailType;

import java.time.Instant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * QListOrder
 *
 * @author shuigedeng
 * @version 2026.03
 * @since 2025-12-19 09:30:45
 */
@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class QListOrder {

    private final String id;
    private final OrderDetailType orderDetailTypeEnum;
    private final String orderDetailType;
    private final String status;
    private final OrderStatus statusEnum;
    private final String description;

    private final String paidPrice;
    private final String paymentType;
    private final Instant paidAt;

    private final Instant createdAt;
    private final boolean invoiceRequested;
    private final boolean invoiceIssued;
}
