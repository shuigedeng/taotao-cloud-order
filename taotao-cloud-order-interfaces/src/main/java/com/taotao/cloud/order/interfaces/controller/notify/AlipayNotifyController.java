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

package com.taotao.cloud.order.interfaces.controller.notify;


import com.taotao.boot.common.model.result.Result;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.order.application.dto.aftersale.command.CreateAfterSaleCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理端-品牌API
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-25 16:16:20
 */
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "回调通知-阿里回调通知API", description = "回调通知-阿里回调通知API")
@RequestMapping("/callback/order/alipay")
public class AlipayNotifyController extends BusinessController {

	@Operation(summary = "阿里支付状态通知", description = "阿里支付状态通知")
	@RequestLogger
	@PostMapping("/pay")
	public Result<Void> payNotify(@Validated @RequestBody CreateAfterSaleCommand brand) {
		return Result.success();
	}


	@Operation(summary = "支付宝退款状态通知", description = "支付宝退款异步通知")
	@PostMapping("/refund")
	public String refundNotify(@Validated @RequestBody CreateAfterSaleCommand brand) {
		// 处理退款通知
		return "success";
	}
}
