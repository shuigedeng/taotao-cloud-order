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

import com.taotao.boot.common.model.result.PageResult;
import com.taotao.boot.common.model.result.Result;
import com.taotao.boot.security.spring.support.utils.SecurityUtils;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.web.utils.OperationalJudgment;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.order.application.dto.order.command.CreateOrderReceiptCommand;
import com.taotao.cloud.order.application.dto.order.query.ReceiptPageQuery;
import com.taotao.cloud.order.application.dto.order.result.ReceiptResult;
import com.taotao.cloud.order.application.service.command.ReceiptCommandService;
import com.taotao.cloud.order.application.service.query.ReceiptQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "店铺端-发票API", description = "店铺端-发票API")
@RequestMapping("/seller/order/receipt")
public class ReceiptSellerController extends BusinessController {

    private final ReceiptQueryService receiptQueryService;
    private final ReceiptCommandService receiptCommandService;

    @Operation(summary = "分页获取", description = "分页获取")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping("/page")
    public Result<PageResult<CreateOrderReceiptCommand>> queryByPage(ReceiptPageQuery receiptPageQry) {
        receiptPageQry.setStoreId(SecurityUtils.queryCurrentUser().queryStoreId());
        return Result.success(receiptQueryService.pageQuery(receiptPageQry));
    }

    @Operation(summary = "通过id获取", description = "通过id获取")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping(value = "/{id}")
    public Result<ReceiptResult> query(@PathVariable String id) {
        return Result.success(OperationalJudgment.judgment(receiptQueryService.queryDetail(id)));
    }

    @Operation(summary = "开发票", description = "开发票")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PostMapping(value = "/{id}/invoicing")
    public Result<ReceiptResult> invoicing(@PathVariable Long id) {
        OperationalJudgment.judgment(receiptQueryService.queryDetail(String.valueOf(id)));
        return Result.success(receiptCommandService.invoicing(id));
    }

    @Operation(summary = "通过订单编号获取", description = "通过订单编号获取")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping(value = "/orderSn/{orderSn}")
    public Result<ReceiptResult> queryByOrderSn(@PathVariable String orderSn) {
        OperationalJudgment.judgment(receiptQueryService.queryByOrderSn(orderSn));
        return Result.success(receiptQueryService.queryByOrderSn(orderSn));
    }
}
