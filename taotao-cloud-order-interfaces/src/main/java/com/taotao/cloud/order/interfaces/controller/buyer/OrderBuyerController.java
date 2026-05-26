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

import com.taotao.boot.common.enums.ResultEnum;
import com.taotao.boot.common.model.result.PageResult;
import com.taotao.boot.common.model.result.Result;
import com.taotao.boot.data.mybatis.mybatisplus.MpUtils;
import com.taotao.boot.security.spring.support.utils.SecurityUtils;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.web.utils.OperationalJudgment;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.order.application.dto.order.query.OrderPageQuery;
import com.taotao.cloud.order.application.dto.order.result.OrderDetailResult;
import com.taotao.cloud.order.application.dto.order.result.OrderSimpleResult;
import com.taotao.cloud.order.application.service.command.OrderCommandService;
import com.taotao.cloud.order.application.service.query.OrderQueryService;
import com.taotao.cloud.order.common.enums.order.OrderStatusEnum;
import com.taotao.cloud.order.domain.entity.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "买家端-订单API", description = "买家端-订单API")
@RequestMapping("/buyer/order/order")
public class OrderBuyerController extends BusinessController {

	private final OrderQueryService orderQueryService;
	private final OrderCommandService orderCommandService;

	@Operation(summary = "查询会员订单列表", description = "查询会员订单列表")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping("/page")
	public Result<PageResult<OrderSimpleResult>> queryMineOrder(OrderPageQuery orderPageQuery) {
		orderPageQuery.setMemberId(SecurityUtils.queryCurrentUser().queryUserId());
		return Result.success(MpUtils.convertMpPage(orderQueryService.pageQuery(orderPageQuery), OrderSimpleResult.class));
	}

	@Operation(summary = "订单明细", description = "订单明细")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/{orderSn}")
	public Result<OrderDetailResult> detail(@NotNull(message = "订单编号不能为空") @PathVariable("orderSn") String orderSn) {
		OrderDetailResult orderDetailResult = orderQueryService.queryDetail(orderSn);
		OperationalJudgment.judgment(orderDetailResult.order());
		return Result.success(orderDetailResult);
	}

	@Operation(summary = "确认收货", description = "确认收货")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/{orderSn}/receiving")
	public Result<Void> receiving(@NotNull(message = "订单编号不能为空") @PathVariable("orderSn") String orderSn) {
		Order order = orderQueryService.queryBySn(orderSn);
		if (order == null) {
			throw new BusinessException(ResultEnum.ORDER_NOT_EXIST);
		}
		if (!order.queryOrderStatus().equals(OrderStatusEnum.DELIVERED.name())) {
			throw new BusinessException(ResultEnum.ORDER_DELIVERED_ERROR);
		}
		orderCommandService.complete(orderSn);
		return Result.success();
	}

	@Operation(summary = "取消订单", description = "取消订单")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/{orderSn}/cancel")
	public Result<Void> cancel(@PathVariable String orderSn, @RequestParam String reason) {
		orderCommandService.cancel(orderSn, reason);
		return Result.success();
	}

	@Operation(summary = "删除订单", description = "删除订单")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/{orderSn}")
	public Result<Void> deleteOrder(@PathVariable String orderSn) {
		OperationalJudgment.judgment(orderQueryService.queryBySn(orderSn));
		orderCommandService.deleteOrder(orderSn);
		return Result.success();
	}

	@Operation(summary = "查询物流踪迹", description = "查询物流踪迹")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/traces/{orderSn}")
	public Result<Traces> queryTraces(@NotBlank(message = "订单编号不能为空") @PathVariable String orderSn) {
		OperationalJudgment.judgment(orderQueryService.queryBySn(orderSn));
		return Result.success(orderQueryService.queryTraces(orderSn));
	}

	@Operation(summary = "开票", description = "开票")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/receipt/{orderSn}")
	public Result<Void> invoice(@NotBlank(message = "订单编号不能为空") @PathVariable String orderSn) {
		OperationalJudgment.judgment(orderQueryService.queryBySn(orderSn));
		orderCommandService.invoice(orderSn);
		return Result.success();
	}
}
