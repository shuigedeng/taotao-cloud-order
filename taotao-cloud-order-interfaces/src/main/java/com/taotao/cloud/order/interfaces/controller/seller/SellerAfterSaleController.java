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

import com.taotao.boot.common.model.result.Result;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.web.utils.OperationalJudgment;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.order.application.dto.aftersale.command.ConfirmCommand;
import com.taotao.cloud.order.application.dto.aftersale.command.ReviewCommand;
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

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "店铺端-售后API", description = "店铺端-售后API")
@RequestMapping("/seller/order/aftersale")
public class SellerAfterSaleController extends BusinessController {

    private final AfterSaleQueryService afterSaleQueryService;
    private final AfterSaleCommandService afterSaleCommandService;

    @Operation(summary = "查看售后服务详情", description = "查看售后服务详情")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping(value = "/query/detail")
    public Result<AfterSaleResult> queryAfterSaleBySn(@Valid SnQuery query) {
		AfterSaleResult result = afterSaleQueryService.queryAfterSaleBySn(query.getSn());
        return Result.success(OperationalJudgment.judgment(result));
    }

//    @Operation(summary = "分页获取售后服务", description = "分页获取售后服务")
//    @RequestLogger
//    @PreAuthorize("hasAuthority('dept:tree:data')")
//    @GetMapping(value = "/page")
//    public Result<PageResult<AfterSaleResult>> queryByPage(AfterSalePageQuery searchParams) {
//        Long storeId = SecurityUtils.queryCurrentUser().queryStoreId();
//        searchParams.setStoreId(storeId);
//        return Result.success(afterSaleQueryService.pageQuery(searchParams));
//    }

//    @Operation(summary = "获取导出售后服务列表列表", description = "获取导出售后服务列表列表")
//    @RequestLogger
//    @PreAuthorize("hasAuthority('dept:tree:data')")
//    @GetMapping(value = "/exportAfterSaleOrder")
//    public Result<List<AfterSaleResult>> exportAfterSaleOrder(AfterSalePageQuery searchParams) {
//        Long storeId = SecurityUtils.queryCurrentUser().queryStoreId();
//        searchParams.setStoreId(storeId);
//        return Result.success(afterSaleQueryService.exportAfterSaleOrder(searchParams));
//    }

    @Operation(summary = "审核售后申请", description = "审核售后申请")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PostMapping(value = "/command/review")
    public Result<Void> review(@Valid @RequestBody ReviewCommand command) {
        afterSaleCommandService.review(command);
        return Result.success();
    }

    @Operation(summary = "卖家确认收货", description = "卖家确认收货")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PostMapping(value = "/confirm/{afterSaleSn}")
    public Result<Void> confirm(@Valid @RequestBody ConfirmCommand command) {
        afterSaleCommandService.storeConfirm(command);
        return Result.success();
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
}
