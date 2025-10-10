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
import com.taotao.cloud.order.application.service.command.AfterSaleCommandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理端,售后API
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:57:09
 */
@AllArgsConstructor
@Validated
@RestController
@Tag(name = "管理端-售后管理API", description = "管理端-售后管理API")
@RequestMapping("/order/manager/aftersale")
public class ManagerAfterSaleController extends BusinessController {

    /**
     * 售后
     */
    private final AfterSaleCommandService afterSaleCommandService;
    //
    // @Operation(summary = "分页获取售后服务", description = "分页获取售后服务")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @GetMapping(value = "/page")
    // public Result<PageResult<AfterSaleCO>> pageQuery(AfterSalePageQry searchParams) {
    //	IPage<AfterSalePO> page = afterSaleCommandService.pageQuery(searchParams);
    //	return Result.success(MpUtils.convertMybatisPage(page, AfterSaleCO.class));
    // }
    //
    // @Operation(summary = "获取导出售后服务列表列表", description = "获取导出售后服务列表列表")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @GetMapping(value = "/exportAfterSaleOrder")
    // public Result<List<AfterSaleCO>> exportAfterSaleOrder(AfterSalePageQry afterSalePageQry) {
    //	List<AfterSalePO> afterSales =
    // afterSaleCommandService.exportAfterSaleOrder(afterSalePageQry);
    //	return Result.success(AfterSaleAssembler.INSTANCE.convert(afterSales));
    // }
    //
    // @Operation(summary = "查看售后服务详情", description = "查看售后服务详情")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @GetMapping(value = "/{sn}")
    // public Result<AfterSaleCO> get(@NotNull(message = "售后单号") @PathVariable("sn") String sn) {
    //	AfterSalePO afterSale = afterSaleCommandService.getAfterSaleBySn(sn);
    //	return Result.success(AfterSaleAssembler.INSTANCE.convert(afterSale));
    // }
    //
    // @Operation(summary = "查看买家退货物流踪迹", description = "查看买家退货物流踪迹")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @GetMapping(value = "/delivery/traces/{sn}")
    // public Result<TracesVO> getDeliveryTraces(@PathVariable String sn) {
    //	return Result.success(afterSaleCommandService.deliveryTraces(sn));
    // }
    //
    // @Operation(summary = "售后线下退款", description = "售后线下退款")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @PutMapping(value = "/refund/{afterSaleSn}")
    // public Result<Boolean> refund(
    //	@NotNull(message = "请选择售后单") @PathVariable String afterSaleSn,
    //	@RequestParam String remark) {
    //	return Result.success(afterSaleCommandService.refund(afterSaleSn, remark));
    // }
    //
    // @Operation(summary = "审核售后申请", description = "审核售后申请")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @PutMapping(value = "/review/{afterSaleSn}")
    // public Result<Boolean> review(
    //	@NotNull(message = "请选择售后单") @PathVariable String afterSaleSn,
    //	@NotNull(message = "请审核") String serviceStatus,
    //	String remark,
    //	BigDecimal actualRefundPrice) {
    //	return Result.success(
    //		afterSaleCommandService.review(afterSaleSn, serviceStatus, remark, actualRefundPrice));
    // }
    //
    // @Operation(summary = "获取商家售后收件地址", description = "获取商家售后收件地址")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @GetMapping(value = "/getStoreAfterSaleAddress/{sn}")
    // public Result<StoreAfterSaleAddressVO> getStoreAfterSaleAddress(
    //	@NotNull(message = "售后单号不能为空") @PathVariable("sn") String sn) {
    //	return Result.success(afterSaleCommandService.getStoreAfterSaleAddressVO(sn));
    // }
}
