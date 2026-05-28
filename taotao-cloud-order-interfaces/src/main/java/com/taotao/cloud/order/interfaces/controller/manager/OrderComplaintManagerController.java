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

import com.taotao.boot.common.model.result.PageResult;
import com.taotao.boot.common.model.result.Result;
import com.taotao.boot.security.spring.support.utils.SecurityUtils;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.order.application.dto.order.command.CreateOrderComplaintCommand;
import com.taotao.cloud.order.application.dto.order.command.CreateOrderComplaintCommunicationCommand;
import com.taotao.cloud.order.application.dto.order.command.CreateOrderComplaintOperationCommand;
import com.taotao.cloud.order.application.dto.order.query.OrderComplaintPageQuery;
import com.taotao.cloud.order.application.dto.order.result.OrderComplaintBaseResult;
import com.taotao.cloud.order.application.dto.order.result.OrderComplaintResult;
import com.taotao.cloud.order.application.service.command.OrderComplaintCommandService;
import com.taotao.cloud.order.application.service.command.OrderComplaintCommunicationCommandService;
import com.taotao.cloud.order.application.service.query.OrderComplaintQueryService;
import com.taotao.cloud.order.common.enums.order.CommunicationOwnerEnum;
import com.taotao.cloud.order.common.enums.order.OrderComplaintStatusEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "管理端-交易投诉管理API", description = "管理端-交易投诉管理API")
@RequestMapping("/manager/order/order/complain")
public class OrderComplaintManagerController extends BusinessController {

    private final OrderComplaintQueryService orderComplaintQueryService;
    private final OrderComplaintCommandService orderComplaintCommandService;
    private final OrderComplaintCommunicationCommandService orderComplaintCommunicationCommandService;

    @Operation(summary = "通过id获取", description = "通过id获取")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping(value = "/{id}")
    public Result<OrderComplaintResult> query(@PathVariable Long id) {
		return Result.success(orderComplaintQueryService.queryOrderComplainById(id));
    }

    @Operation(summary = "分页获取", description = "分页获取")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping("/page")
    public Result<PageResult<OrderComplaintBaseResult>> pageQuery(
            @Validated OrderComplaintPageQuery orderComplaintPageQry) {
        PageResult<OrderComplaintBaseResult> page = orderComplaintQueryService.pageQuery(orderComplaintPageQry);
        return Result.success(page);
    }

    @Operation(summary = "更新数据", description = "更新数据")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PostMapping("/{id}")
	public Result<Void> update(@PathVariable Long id,
			                          @Validated @RequestBody CreateOrderComplaintCommand orderComplaintAddCmd) {
		orderComplaintCommandService.updateOrderComplain(id, orderComplaintAddCmd);
		return Result.success();
    }

    @Operation(summary = "添加交易投诉对话", description = "添加交易投诉对话")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PostMapping("/communication/{complainId}")
	public Result<Void> addCommunication(
			@PathVariable("complainId") Long complainId,
			@Validated @RequestBody CreateOrderComplaintCommunicationCommand orderComplaintCommunicationAddCmd) {
		String username = SecurityUtils.queryCurrentUser().queryUsername();
		Long userId = SecurityUtils.queryCurrentUser().queryUserId();
		orderComplaintCommunicationCommandService.addCommunication(complainId,
				orderComplaintCommunicationAddCmd, CommunicationOwnerEnum.PLATFORM.name(),
				username, userId);
		return Result.success();
    }

    @Operation(summary = "修改状态", description = "修改状态")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PostMapping(value = "/status")
	public Result<Void> updateStatus(
			@Validated @RequestBody CreateOrderComplaintOperationCommand orderComplaintOperationAddCmd) {
		orderComplaintCommandService.updateOrderComplainByStatus(orderComplaintOperationAddCmd);
		return Result.success();
    }

    @Operation(summary = "仲裁", description = "仲裁")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PostMapping(value = "/complete/{id}")
	public Result<Void> complete(@PathVariable Long id, String arbitrationResult) {
		CreateOrderComplaintOperationCommand orderComplaintOperationAddCmd = CreateOrderComplaintOperationCommand.builder()
				.complainId(id)
				.arbitrationResult(arbitrationResult)
				.complainStatus(OrderComplaintStatusEnum.COMPLETE.name())
				.build();
		orderComplaintCommandService.updateOrderComplainByStatus(orderComplaintOperationAddCmd);
		return Result.success();
    }
}
