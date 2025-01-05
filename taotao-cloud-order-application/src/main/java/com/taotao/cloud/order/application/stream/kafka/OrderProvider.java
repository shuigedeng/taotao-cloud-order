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

package com.taotao.cloud.order.application.stream.kafka;

import jakarta.annotation.Resource;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderProvider {

    @Resource
    private TaoTaoCloudSource source;

    public String send(String content) {
        source.orderOutput()
                .send(MessageBuilder.withPayload(content)
                        // .setHeader("routingKey", "login.user.succeed")
                        // .setHeader("version", "1.0")
                        // .setHeader("x-delay", 5000)
                        .build());
        return "success";
    }
}
