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

package com.taotao.cloud.order.interfaces.controller.buyer;

import com.taotao.boot.common.model.result.PageResult;
import com.taotao.boot.common.model.result.Result;
import com.taotao.boot.data.mybatis.mybatisplus.MpUtils;
import com.taotao.boot.security.spring.support.utils.SecurityUtils;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.web.utils.OperationalJudgment;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.order.application.dto.order.command.CreateOrderComplaintCommand;
import com.taotao.cloud.order.application.dto.order.command.CreateOrderComplaintCommunicationCommand;
import com.taotao.cloud.order.application.dto.order.query.OrderComplaintPageQuery;
import com.taotao.cloud.order.application.dto.order.result.OrderComplaintBaseResult;
import com.taotao.cloud.order.application.dto.order.result.OrderComplaintResult;
import com.taotao.cloud.order.application.service.command.OrderComplaintCommandService;
import com.taotao.cloud.order.application.service.command.OrderComplaintCommunicationCommandService;
import com.taotao.cloud.order.application.service.query.OrderComplaintQueryService;
import com.taotao.cloud.order.common.enums.order.CommunicationOwnerEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@Tag(name = "买家端-交易投诉API", description = "买家端-交易投诉API")
@RequestMapping("/buyer/order/order/complain")
public class OrderComplaintBuyerController extends BusinessController {

	private final OrderComplaintQueryService orderComplaintQueryService;
	private final OrderComplaintCommandService orderComplaintCommandService;
	private final OrderComplaintCommunicationCommandService orderComplaintCommunicationCommandService;

	@Operation(summary = "通过id获取", description = "通过id获取")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/{id}")
	public Result<OrderComplaintResult> query(@PathVariable Long id) {
		OrderComplaintResult orderComplaintResult =
			OperationalJudgment.judgment(orderComplaintQueryService.queryOrderComplainById(id));
		return Result.success(orderComplaintResult);
	}

	@Operation(summary = "分页获取", description = "分页获取")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping("/page")
	public Result<PageResult<OrderComplaintBaseResult>> query(@Validated OrderComplaintPageQuery orderComplaintPageQuery) {
		return Result.success(
			MpUtils.convertMpPage(orderComplaintQueryService.pageQuery(orderComplaintPageQuery), OrderComplaintBaseResult.class));
	}

	@Operation(summary = "添加交易投诉", description = "添加交易投诉")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping
	public Result<OrderComplaintResult> add(@Valid @RequestBody CreateOrderComplaintCommand orderComplaintAddCommand) {
		return Result.success(orderComplaintCommandService.addOrderComplain(orderComplaintAddCommand));
	}

	@Operation(summary = "添加交易投诉对话", description = "添加交易投诉对话")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping("/communication/{complainId}")
	public Result<Void> addCommunication(
			@PathVariable("complainId") Long complainId,
			@Validated @RequestBody CreateOrderComplaintCommunicationCommand orderComplaintCommunicationAddCommand) {
		String username = SecurityUtils.queryCurrentUser().queryNickname();
		Long userId = SecurityUtils.queryCurrentUser().queryUserId();
		orderComplaintCommunicationCommandService.addCommunication(complainId,
				orderComplaintCommunicationAddCommand, CommunicationOwnerEnum.BUYER.name(),
				username, userId);
		return Result.success();
	}

	@Operation(summary = "取消售后", description = "取消售后")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/status/{id}")
	public Result<Void> cancel(@PathVariable Long id) {
		orderComplaintCommandService.cancel(id);
		return Result.success();
	}
}
