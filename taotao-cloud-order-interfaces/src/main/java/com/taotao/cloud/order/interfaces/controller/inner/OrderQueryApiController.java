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

package com.taotao.cloud.order.interfaces.controller.inner;

import com.taotao.boot.common.model.request.Request;
import com.taotao.boot.common.model.result.Result;
import com.taotao.boot.webagg.controller.InnerController;
import com.taotao.cloud.order.api.inner.dto.query.OrderApiQuery;
import com.taotao.cloud.order.api.inner.dto.response.OrderApiResponse;
import com.taotao.cloud.order.api.inner.query.OrderQueryApi;
import com.taotao.cloud.order.application.service.query.OrderQueryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/sys/dict")
public class OrderQueryApiController extends InnerController implements OrderQueryApi {

    private final OrderQueryService orderQueryService;

    public OrderQueryApiController(OrderQueryService orderQueryService) {
        this.orderQueryService = orderQueryService;
    }

    @Override
    @PostMapping("/code")
    public Result<OrderApiResponse> findByCode(@Validated @RequestBody Request<OrderApiQuery> dictQueryApiRequest) {
        OrderApiQuery query = dictQueryApiRequest.getData();
        OrderApiResponse response = orderQueryService.findByCode(query);
        return Result.success(response);
    }
}
