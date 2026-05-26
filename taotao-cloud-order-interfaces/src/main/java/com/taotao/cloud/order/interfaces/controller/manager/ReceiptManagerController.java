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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.boot.common.model.result.PageResult;
import com.taotao.boot.common.model.result.Result;
import com.taotao.boot.data.mybatis.mybatisplus.MpUtils;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.order.application.dto.order.command.OrderReceiptAddCommand;
import com.taotao.cloud.order.application.dto.order.query.ReceiptPageQuery;
import com.taotao.cloud.order.application.service.query.ReceiptQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "管理端-发票记录管理API", description = "管理端-发票记录管理API")
@RequestMapping("/manager/order/receipt")
public class ReceiptManagerController extends BusinessController {

    private final ReceiptQueryService receiptQueryService;

    @Operation(summary = "获取发票分页信息", description = "获取发票分页信息")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping("/tree")
    public Result<PageResult<OrderReceiptAddCommand>> queryPage(ReceiptPageQuery receiptPageQry) {
        IPage<OrderReceiptAddCommand> page = receiptQueryService.pageQuery(receiptPageQry);
        return Result.success(MpUtils.convertMpPage(page, OrderReceiptAddCommand.class));
    }
}
