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

package com.taotao.cloud.order.api.inner.command;

import com.taotao.boot.common.model.request.Request;
import com.taotao.boot.common.model.response.Response;
import com.taotao.cloud.order.api.inner.dto.command.OrderApiCommand;
import com.taotao.cloud.order.api.inner.dto.query.OrderApiQuery;
import com.taotao.cloud.order.api.inner.dto.response.OrderApiResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

/**
 * 订单命令 API
 * <p>提供订单相关的命令操作接口（远程调用）</p>
 *
 * @author shuigedeng
 * @since 2020/5/2 16:42
 */
@HttpExchange
public interface OrderCommandApi {

    /**
     * 字典列表code查询
     *
     * @param request 请求参数
     * @return 响应对象
     * @since 2022-06-29 21:40:21
     */
    @PostExchange("/sys/dict/code/command")
    Response<OrderApiResponse> createOrder(@RequestBody Request<OrderApiCommand> request);

}
