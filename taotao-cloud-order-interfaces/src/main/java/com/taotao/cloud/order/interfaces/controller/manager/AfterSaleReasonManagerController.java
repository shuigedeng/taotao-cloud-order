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
import com.taotao.cloud.order.application.dto.aftersale.command.UpdateCAfterSaleReasonCommand;
import com.taotao.cloud.order.application.dto.aftersale.query.AfterSaleReasonPageQuery;
import com.taotao.cloud.order.application.dto.aftersale.result.AfterSaleReasonResult;
import com.taotao.cloud.order.application.service.command.AfterSaleReasonCommandService;
import com.taotao.cloud.order.application.service.query.AfterSaleReasonQueryService;
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
@Tag(name = "管理端-售后原因管理API", description = "管理端-售后原因管理API")
@RequestMapping("/manager/order/aftersale/reason")
public class AfterSaleReasonManagerController extends BusinessController {

    private final AfterSaleReasonQueryService afterSaleReasonQueryService;
    private final AfterSaleReasonCommandService afterSaleReasonCommandService;

    @Operation(summary = "查看售后原因", description = "查看售后原因")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping(value = "/{id}")
    public Result<AfterSaleReasonResult> queryById(@PathVariable String id) {
        AfterSaleReasonResult afterSaleReasonResult = afterSaleReasonQueryService.queryById(id);
        return Result.success(afterSaleReasonResult);
    }

    @Operation(summary = "分页获取售后原因", description = "分页获取售后原因")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @GetMapping(value = "/page")
    public Result<PageResult<AfterSaleReasonResult>> queryByPage(
            @Validated AfterSaleReasonPageQuery afterSaleReasonPageQry) {
        PageResult<AfterSaleReasonResult> page = afterSaleReasonQueryService.pageQuery(afterSaleReasonPageQry);
        return Result.success(page);
    }

    @Operation(summary = "添加售后原因", description = "添加售后原因")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PostMapping
	public Result<Void> save(@Validated @RequestBody UpdateCAfterSaleReasonCommand afterSaleReasonUpdateCmd) {
		afterSaleReasonCommandService.save(afterSaleReasonUpdateCmd);
		return Result.success();
    }

    @Operation(summary = "修改售后原因", description = "修改售后原因")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PostMapping("/{id}")
	public Result<Void> update(
			@Validated @RequestBody UpdateCAfterSaleReasonCommand afterSaleReasonUpdateCmd,
			@PathVariable("id") Long id) {
		afterSaleReasonCommandService.editAfterSaleReason(id, afterSaleReasonUpdateCmd);
		return Result.success();
    }

    @Operation(summary = "删除售后原因", description = "删除售后原因")
    @RequestLogger
    @PreAuthorize("hasAuthority('dept:tree:data')")
    @PostMapping(value = "/{id}")
	public Result<Void> delAllByIds(@PathVariable String id) {
		afterSaleReasonCommandService.removeById(id);
		return Result.success();
    }
}
