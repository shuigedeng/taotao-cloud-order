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

package com.taotao.cloud.order.interfaces.controller.seller;

import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.order.application.service.command.OrderCommandService;
import com.taotao.cloud.order.application.service.command.OrderLogCommandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 店铺端,订单日志API
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:57:40
 */
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "店铺端-订单日志API", description = "店铺端-订单日志API")
@RequestMapping("/seller/order/order/log")
public class OrderLogSellerController extends BusinessController {

    private final OrderLogCommandService orderLogService;

    private final OrderCommandService orderCommandService;

    //	@Operation(summary = "通过订单编号获取订单日志", description = "通过订单编号获取订单日志")
    //	@RequestLogger
    //	@PreAuthorize("hasAuthority('dept:tree:data')")
    //	@GetMapping(value = "/{orderSn}")
    //	public Result<List<OrderLogPO>> get(@PathVariable String orderSn) {
    //		OperationalJudgment.judgment(orderCommandService.getBySn(orderSn));
    //		return Result.success(orderLogService.getOrderLog(orderSn));
    //	}
}
