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

package com.taotao.cloud.order.domain.order.event;

import static lombok.AccessLevel.PRIVATE;

import com.taotao.cloud.order.domain.order.valueobject.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

@Getter
@TypeAlias("ORDER_WX_TRANSFER_UPDATED_EVENT")
@NoArgsConstructor(access = PRIVATE)
public class OrderWxTransferUpdatedEvent extends OrderUpdatedEvent {

    public OrderWxTransferUpdatedEvent(Long orderId, User user) {
        //        super(ORDER_WX_TRANSFER_UPDATED, orderId, user);
    }
}
