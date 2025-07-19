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

package com.taotao.cloud.order.application.dto.order.query;

import static lombok.AccessLevel.PRIVATE;

import com.taotao.cloud.order.domain.order.valueobject.detail.OrderDetail;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class QDetailedOrder {
    private final String id;
    private final String description;
    private final String orderDetailType;
    private final OrderDetail orderDetail;
    private final String status;

    private final String discountedTotalPrice;
    private final String paymentType;
    private final String wxTxnId;
    private final String bankTransferCode;
    private final String bankName;
    private final String bankTransferAccountId;
    private final Instant paidAt;

    private final boolean invoiceRequested;
    private final boolean invoiceIssued;
    private final String invoiceTitle;
    private final String invoiceType;
    private final String invoiceEmail;

    private final String carrier;
    private final String deliveryOrderId;

    private final Instant createdAt;
    private final String createdBy;
    private final String creator;
}
