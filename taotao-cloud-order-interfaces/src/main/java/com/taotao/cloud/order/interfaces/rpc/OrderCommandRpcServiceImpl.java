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

package com.taotao.cloud.order.interfaces.rpc;

import com.taotao.boot.common.model.request.Request;
import com.taotao.boot.common.model.response.Response;
import com.taotao.cloud.order.api.rpc.command.OrderCommandRpcService;
import com.taotao.cloud.order.api.rpc.dto.request.OrderQueryRpcRequest;
import com.taotao.cloud.order.api.rpc.dto.response.OrderQueryRpcResponse;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@DubboService(interfaceClass = OrderCommandRpcService.class, validation = "true")
public class OrderCommandRpcServiceImpl implements OrderCommandRpcService {

	@Override
	public Response<OrderQueryRpcResponse> query( Request<OrderQueryRpcRequest> orderQueryRpcRequest ) {
		return null;
	}
}
