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
import com.taotao.cloud.order.application.dto.aftersale.query.AfterSalePageQuery;
import com.taotao.cloud.order.application.dto.aftersale.result.AfterSaleResult;
import com.taotao.cloud.order.application.dto.aftersale.result.StoreAfterSaleAddressResult;
import com.taotao.cloud.order.application.service.command.AfterSaleCommandService;
import com.taotao.cloud.order.application.service.query.AfterSaleQueryService;
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
@Tag(name = "管理端-售后管理API", description = "管理端-售后管理API")
@RequestMapping("/manager/order/aftersale")
public class AfterSaleManagerController extends BusinessController {

    private final AfterSaleCommandService afterSaleCommandService;
    private final AfterSaleQueryService afterSaleQueryService;

    @Operation(summary = "分页获取售后服务", description = "分页获取售后服务")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping(value = "/page")
    public Result<PageResult<AfterSaleResult>> pageQuery(AfterSalePageQuery searchParams) {
        PageResult<AfterSaleResult> page = afterSaleQueryService.pageQuery(searchParams);
        return Result.success(page);
    }

    @Operation(summary = "获取导出售后服务列表列表", description = "获取导出售后服务列表列表")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping(value = "/exportAfterSaleOrder")
    public Result<List<AfterSaleResult>> exportAfterSaleOrder(AfterSalePageQuery afterSalePageQry) {
        List<AfterSaleResult> afterSales = afterSaleQueryService.exportAfterSaleOrder(afterSalePageQry);
        return Result.success(afterSales);
    }

    @Operation(summary = "查看售后服务详情", description = "查看售后服务详情")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping(value = "/{sn}")
    public Result<AfterSaleResult> query(@NotNull(message = "售后单号") @PathVariable("sn") String sn) {
        AfterSaleResult afterSale = afterSaleQueryService.queryAfterSaleBySn(sn);
        return Result.success(afterSale);
    }

    @Operation(summary = "查看买家退货物流踪迹", description = "查看买家退货物流踪迹")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping(value = "/delivery/traces/{sn}")
    public Result<?> queryDeliveryTraces(@PathVariable String sn) {
		return Result.success(afterSaleQueryService.deliveryTraces(sn));
    }

    @Operation(summary = "售后线下退款", description = "售后线下退款")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PostMapping(value = "/refund/{afterSaleSn}")
	public Result<Void> refund(
			@NotNull(message = "请选择售后单") @PathVariable String afterSaleSn,
			@RequestParam String remark) {
		afterSaleCommandService.refund(afterSaleSn, remark);
		return Result.success();
    }

    @Operation(summary = "审核售后申请", description = "审核售后申请")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PostMapping(value = "/review/{afterSaleSn}")
	public Result<Void> review(
			@NotNull(message = "请选择售后单") @PathVariable String afterSaleSn,
			@NotNull(message = "请审核") String serviceStatus,
			String remark,
			BigDecimal actualRefundPrice) {
		afterSaleCommandService.review(afterSaleSn, serviceStatus, remark, actualRefundPrice);
		return Result.success();
    }

    @Operation(summary = "获取商家售后收件地址", description = "获取商家售后收件地址")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping(value = "/queryStoreAfterSaleAddress/{sn}")
    public Result<StoreAfterSaleAddressResult> queryStoreAfterSaleAddress(
            @NotNull(message = "售后单号不能为空") @PathVariable("sn") String sn) {
        return Result.success(afterSaleQueryService.queryStoreAfterSaleAddress(sn));
    }
}
