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

import com.taotao.boot.webagg.controller.BusinessController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 买家端,订单API
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:57:00
 */
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "买家端-订单API", description = "买家端-订单API")
@RequestMapping("/order/buyer/order")
public class BuyerOrderController extends BusinessController {

    // private final OrderCommandService orderCommandService;
    //
    // @Operation(summary = "查询会员订单列表", description = "查询会员订单列表")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @GetMapping("/page")
    // public Result<PageResult<OrderSimpleCO>> queryMineOrder(OrderPageQry orderPageQry) {
    //	SecurityUser currentUser = SecurityUtils.getCurrentUser();
    //	orderPageQry.setMemberId(currentUser.getUserId());
    //	IPage<OrderSimpleCO> page = orderCommandService.pageQuery(orderPageQry);
    //	return Result.success(MpUtils.convertMybatisPage(page, OrderSimpleCO.class));
    // }
    //
    // @Operation(summary = "订单明细", description = "订单明细")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @GetMapping(value = "/{orderSn}")
    // public Result<OrderDetailCO> detail(@NotNull(message = "订单编号不能为空") @PathVariable("orderSn")
    // String orderSn) {
    //	OrderDetailCO orderDetailCO = orderCommandService.queryDetail(orderSn);
    //	OperationalJudgment.judgment(orderDetailCO.order());
    //	return Result.success(orderDetailCO);
    // }
    //
    // @Operation(summary = "确认收货", description = "确认收货")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @PostMapping(value = "/{orderSn}/receiving")
    // public Result<Boolean> receiving(@NotNull(message = "订单编号不能为空") @PathVariable("orderSn")
    // String orderSn) {
    //	Order order = orderCommandService.getBySn(orderSn);
    //	if (order == null) {
    //		throw new BusinessException(ResultEnum.ORDER_NOT_EXIST);
    //	}
    //	// 判定是否是待收货状态
    //	if (!order.getOrderStatus().equals(OrderStatusEnum.DELIVERED.name())) {
    //		throw new BusinessException(ResultEnum.ORDER_DELIVERED_ERROR);
    //	}
    //	orderCommandService.complete(orderSn);
    //	return Result.success(true);
    // }
    //
    // @Operation(summary = "取消订单", description = "取消订单")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @PostMapping(value = "/{orderSn}/cancel")
    // public Result<Boolean> cancel(@PathVariable String orderSn, @RequestParam String reason) {
    //	orderCommandService.cancel(orderSn, reason);
    //	return Result.success(true);
    // }
    //
    // @Operation(summary = "删除订单", description = "删除订单")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @DeleteMapping(value = "/{orderSn}")
    // public Result<Boolean> deleteOrder(@PathVariable String orderSn) {
    //	OperationalJudgment.judgment(orderCommandService.getBySn(orderSn));
    //	orderCommandService.deleteOrder(orderSn);
    //	return Result.success(true);
    // }
    //
    // @Operation(summary = "查询物流踪迹", description = "查询物流踪迹")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @PostMapping(value = "/traces/{orderSn}")
    // public Result<Traces> getTraces(@NotBlank(message = "订单编号不能为空") @PathVariable String orderSn)
    // {
    //	OperationalJudgment.judgment(orderCommandService.getBySn(orderSn));
    //	return Result.success(orderCommandService.getTraces(orderSn));
    // }
    //
    // @Operation(summary = "开票", description = "开票")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @PostMapping(value = "/receipt/{orderSn}")
    // public Result<Boolean> invoice(@NotBlank(message = "订单编号不能为空") @PathVariable String orderSn)
    // {
    //	OperationalJudgment.judgment(orderCommandService.getBySn(orderSn));
    //	return Result.success(orderCommandService.invoice(orderSn));
    // }
}
