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
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.order.application.dto.order.query.OrderPageQuery;
import com.taotao.cloud.order.application.dto.order.command.TradeAddCmd;
import com.taotao.cloud.order.application.dto.order.result.OrderDetailResult;
import com.taotao.cloud.order.application.dto.order.result.OrderResult;
import com.taotao.cloud.order.application.dto.order.result.OrderSimpleResult;
import com.taotao.cloud.order.application.service.command.OrderCommandService;
import com.taotao.cloud.order.application.service.command.OrderPriceCommandService;
import com.taotao.cloud.order.application.service.query.OrderQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
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
@Tag(name = "管理端-订单管理API", description = "管理端-订单管理API")
@RequestMapping("/manager/order/order")
public class OrderManagerController extends BusinessController {

    private final OrderQueryService orderQueryService;
    private final OrderCommandService orderCommandService;
    private final OrderPriceCommandService orderPriceCommandService;

    @Operation(summary = "查询订单列表分页", description = "查询订单列表分页")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping("/tree")
    public Result<PageResult<OrderSimpleResult>> queryMineOrder(OrderPageQuery orderPageQry) {
        PageResult<OrderSimpleResult> page = orderQueryService.pageQuery(orderPageQry);
        return Result.success(page);
    }

    @Operation(summary = "查询订单导出列表", description = "查询订单导出列表")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping("/queryExportOrder")
    public Result<List<OrderResult>> queryExportOrder(OrderPageQuery orderPageQry) {
        List<OrderResult> list = orderQueryService.queryExportOrder(orderPageQry);
        return Result.success(list);
    }

    @Operation(summary = "订单明细", description = "订单明细")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping(value = "/{orderSn}")
    public Result<OrderDetailResult> detail(@PathVariable String orderSn) {
        return Result.success(orderQueryService.queryDetail(orderSn));
    }

    @Operation(summary = "确认收款", description = "确认收款")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PostMapping(value = "/{orderSn}/pay")
	public Result<Void> payOrder(@PathVariable String orderSn) {
		orderPriceCommandService.adminPayOrder(orderSn);
		return Result.success();
    }

    @Operation(summary = "修改收货人信息", description = "修改收货人信息")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PostMapping(value = "/{orderSn}/consignee")
    public Result<OrderResult> consignee(
            @NotNull(message = "参数非法") @PathVariable String orderSn,
            @Validated TradeAddCmd.MemberAddressDTO memberAddressDTO) {
        return Result.success(orderCommandService.updateConsignee(orderSn, memberAddressDTO));
    }

    @Operation(summary = "修改订单价格", description = "修改订单价格")
    @RequestLogger
    @PostMapping(value = "/{orderSn}/price")
	public Result<Void> updateOrderPrice(
			@PathVariable String orderSn,
			@NotNull(message = "订单价格不能为空") @RequestParam BigDecimal price) {
		orderPriceCommandService.updatePrice(orderSn, price);
		return Result.success();
    }

    @Operation(summary = "取消订单", description = "取消订单")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PostMapping(value = "/{orderSn}/cancel")
    public Result<OrderResult> cancel(@PathVariable String orderSn, @RequestParam String reason) {
        return Result.success(orderCommandService.cancel(orderSn, reason));
    }

    @Operation(summary = "查询物流踪迹", description = "查询物流踪迹")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PostMapping(value = "/traces/{orderSn}")
    public Result<?> queryTraces(
            @jakarta.validation.constraints.NotBlank(message = "订单编号不能为空") @PathVariable String orderSn) {
		return Result.success(orderQueryService.queryTraces(orderSn));
    }
}
