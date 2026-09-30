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

package com.taotao.cloud.order.interfaces.controller.admin;

import com.taotao.boot.common.model.result.PageResult;
import com.taotao.boot.common.model.result.Result;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.order.application.dto.aftersale.command.RefundCommand;
import com.taotao.cloud.order.application.dto.aftersale.command.ReviewCommand;
import com.taotao.cloud.order.application.dto.aftersale.query.AfterSalePageQuery;
import com.taotao.cloud.order.application.dto.aftersale.query.SnQuery;
import com.taotao.cloud.order.application.dto.aftersale.result.AfterSaleResult;
import com.taotao.cloud.order.application.dto.aftersale.result.StoreAfterSaleAddressResult;
import com.taotao.cloud.order.application.service.command.AfterSaleCommandService;
import com.taotao.cloud.order.application.service.query.AfterSaleQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "管理端-售后管理API", description = "管理端-售后管理API")
@RequestMapping("/admin/order/aftersale")
public class AdminAfterSaleController extends BusinessController {

    private final AfterSaleCommandService afterSaleCommandService;
    private final AfterSaleQueryService afterSaleQueryService;

    @Operation(summary = "分页获取售后服务", description = "分页获取售后服务")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping(value = "/query/page")
    public Result<PageResult<AfterSaleResult>> pageQuery(@Valid AfterSalePageQuery query) {
        PageResult<AfterSaleResult> result = afterSaleQueryService.queryPage(query);
        return Result.success(result);
    }

    @Operation(summary = "获取导出售后服务列表列表", description = "获取导出售后服务列表列表")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping(value = "/query/export-aftersale-order")
    public Result<List<AfterSaleResult>> exportAfterSaleOrder(@Valid AfterSalePageQuery query) {
        List<AfterSaleResult> result = afterSaleQueryService.exportAfterSaleOrder(query);
        return Result.success(result);
    }

    @Operation(summary = "查看售后服务详情", description = "查看售后服务详情")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping(value = "/query/sn")
    public Result<AfterSaleResult> query(@Valid SnQuery query) {
        AfterSaleResult result = afterSaleQueryService.queryAfterSaleBySn(query.getSn());
        return Result.success(result);
    }

    @Operation(summary = "查看买家退货物流踪迹", description = "查看买家退货物流踪迹")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping(value = "/query/delivery-traces")
    public Result<?> queryDeliveryTraces(@Valid SnQuery query) {
		return Result.success(afterSaleQueryService.deliveryTraces(query.getSn()));
    }

	@Operation(summary = "获取商家售后收件地址", description = "获取商家售后收件地址")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/query/store-aftersale-address")
	public Result<StoreAfterSaleAddressResult> queryStoreAfterSaleAddress(@Valid SnQuery query) {
		return Result.success(afterSaleQueryService.queryStoreAfterSaleAddress(query.getSn()));
	}

    @Operation(summary = "售后线下退款", description = "售后线下退款")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PostMapping(value = "/command/refund")
	public Result<Void> refund(@Valid @RequestBody RefundCommand command) {
		afterSaleCommandService.refund(command);
		return Result.success();
    }

    @Operation(summary = "审核售后申请", description = "审核售后申请")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PostMapping(value = "/command/review/{afterSaleSn}")
	public Result<Void> review(@Valid @RequestBody ReviewCommand command) {
		afterSaleCommandService.review(command);
		return Result.success();
    }


}
