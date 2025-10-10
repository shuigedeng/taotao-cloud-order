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

package com.taotao.cloud.order.interfaces.controller.manager;

import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.order.application.service.command.OrderCommandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理端,收款日志API
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:57:22
 */
@AllArgsConstructor
@Validated
@RestController
@Tag(name = "管理端-支付日志管理API", description = "管理端-支付日志管理API")
@RequestMapping("/order/manager/payment/log")
public class ManagerPaymentLogController extends BusinessController {

    private final OrderCommandService orderCommandService;

    // @Operation(summary = "分页获取支付日志", description = "分页获取支付日志")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @GetMapping("/tree")
    // public Result<IPage<PaymentLogCO>> getByPage(Order order, SearchVO searchVo) {
    //	return Result.success(
    //		orderCommandService.queryPaymentLogs(PageUtil.initPage(page),
    //			PageUtil.initWrapper(order, searchVo)));
    // }
}
