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

import com.taotao.boot.webagg.controller.BusinessController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理端,售后原因API
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:57:11
 */
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "管理端-售后原因管理API", description = "管理端-售后原因管理API")
@RequestMapping("/manager/order/aftersale/reason")
public class AfterSaleReasonManagerController extends BusinessController {

    /// **
    // * 售后原因
    // */
    // private final AfterSaleReasonCommandService afterSaleReasonCommandService;
    //
    // @Operation(summary = "查看售后原因", description = "查看售后原因")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @GetMapping(value = "/{id}")
    // public Result<AfterSaleReasonCO> getById(@PathVariable String id) {
    //	AfterSaleReasonPO afterSaleReasonPO = afterSaleReasonCommandService.getById(id);
    //	return Result.success(AfterSaleReasonAssembler.INSTANCE.convert(afterSaleReasonPO));
    // }
    //
    // @Operation(summary = "分页获取售后原因", description = "分页获取售后原因")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @GetMapping(value = "/page")
    // public Result<PageResult<AfterSaleReasonCO>> getByPage(
    //	@Validated AfterSaleReasonPageQry afterSaleReasonPageQry) {
    //	IPage<AfterSaleReasonPO> page =
    // afterSaleReasonCommandService.pageQuery(afterSaleReasonPageQry);
    //	return Result.success(MpUtils.convertMybatisPage(page, AfterSaleReasonCO.class));
    // }
    //
    // @Operation(summary = "添加售后原因", description = "添加售后原因")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @PostMapping
    // public Result<Boolean> save(@Validated @RequestBody AfterSaleReasonUpdateCmd
    // afterSaleReasonUpdateCmd) {
    //	return Result.success(afterSaleReasonCommandService.save(
    //		AfterSaleReasonAssembler.INSTANCE.convert(afterSaleReasonUpdateCmd)));
    // }
    //
    // @Operation(summary = "修改售后原因", description = "修改售后原因")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @PostMapping("/{id}")
    // public Result<Boolean> update(
    //	@Validated @RequestBody AfterSaleReasonUpdateCmd afterSaleReasonUpdateCmd,
    //	@PathVariable("id") Long id) {
    //	AfterSaleReasonPO afterSaleReasonPO = AfterSaleReasonAssembler.INSTANCE.convert(
    //		afterSaleReasonUpdateCmd);
    //	afterSaleReasonPO.setId(id);
    //	return Result.success(afterSaleReasonCommandService.editAfterSaleReason(afterSaleReasonPO));
    // }
    //
    // @Operation(summary = "删除售后原因", description = "删除售后原因")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @PostMapping(value = "/{id}")
    // public Result<Boolean> delAllByIds(@PathVariable String id) {
    //	return Result.success(afterSaleReasonCommandService.removeById(id));
    // }
}
