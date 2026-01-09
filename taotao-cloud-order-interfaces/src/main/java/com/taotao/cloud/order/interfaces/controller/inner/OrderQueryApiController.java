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
import com.taotao.boot.common.model.response.Response;
import com.taotao.boot.webagg.controller.InnerController;
import com.taotao.cloud.order.api.inner.command.OrderCommandApi;
import com.taotao.cloud.order.api.inner.dto.request.OrderQueryApiRequest;
import com.taotao.cloud.order.api.inner.dto.response.OrderApiResponse;
import com.taotao.cloud.order.api.inner.query.OrderQueryApi;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/sys/feign/dict")
public class OrderQueryApiController extends InnerController implements OrderQueryApi {

	@Override
	public Response<OrderApiResponse> findByCode( Request<OrderQueryApiRequest> dictQueryApiRequest ) {
		return null;
	}

//	@Override
//	@NotAuth
//	@Idempotent(perFix = "findByCode")
//	@Limit(key = "limitTest", period = 10, count = 3)
//	@SentinelResource("findByCode")
//	public DictApiResponse findByCode( @RequestParam(value = "code") String code ) {
//		//		if ("sd".equals(code)) {
//		//			throw new BusinessException("我出错了");
//		//			// try {
//		//			//	Thread.sleep(100000000000L);
//		//			// } catch (InterruptedException e) {
//		//			//	throw new RuntimeException(e);
//		//			// }
//		//		}
//		//		Dict dict = service().findByCode(code);
//		//		return DictConvert.INSTANCE.convert(dict);
//		return null;
//	}
//
//	@Override
//	@Operation(summary = "test", description = "test")
//	@RequestLogger
//	@NotAuth
//	@Idempotent(perFix = "test")
//	@TLogAspect(
//		value = {"code"},
//		pattern = "{{}}",
//		joint = ",",
//		str = "nihao")
//	@Limit(key = "limitTest", period = 10, count = 3)
//	@GuavaLimit
//	@SentinelResource("test")
//	@GetMapping("/test")
//	public DictApiResponse test( @RequestParam(value = "id") String id ) {
//		LogUtils.info("sldfkslfdjalsdfkjalsfdjl");
//		//		Dict dict = service().findByCode(id);
//		//
//		//		Future<Dict> asyncByCode = service().findAsyncByCode(id);
//		//
//		//		Dict dict1;
//		//		try {
//		//			dict1 = asyncByCode.get();
//		//		} catch (InterruptedException | ExecutionException e) {
//		//			throw new RuntimeException(e);
//		//		}
//		//
//		//		LogUtils.info("我在等待你");
//
//		return null;
//		// return IDictMapStruct.INSTANCE.dictToFeignDictRes(dict);
//	}
}
