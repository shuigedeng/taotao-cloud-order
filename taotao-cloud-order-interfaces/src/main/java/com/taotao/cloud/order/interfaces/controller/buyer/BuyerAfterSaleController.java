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
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 买家端,售后管理API
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-07 20:31:22
 */
@AllArgsConstructor
@Validated
@RestController
@Tag(name = "买家端-售后API", description = "买家端-售后API")
@RequestMapping("/order/buyer/aftersale")
public class BuyerAfterSaleController extends BusinessController {

    ///**
    // * 售后
    // */
    //private final AfterSaleCommandService afterSaleCommandService;
    ///**
    // * 售后原因
    // */
    //private final AfterSaleReasonCommandService afterSaleReasonCommandService;
    ///**
    // * 售后日志
    // */
    //private final AfterSaleLogCommandService afterSaleLogCommandService;
	//
    //@Operation(summary = "查看售后服务详情", description = "查看售后服务详情")
    //@RequestLogger
    //@PreAuthorize("hasAuthority('dept:tree:data')")
    //@GetMapping(value = "/{sn}")
    //public Result<AfterSaleCO> getAfterSaleBySn(
    //        @NotBlank(message = "售后单号不能为空") @PathVariable String sn) {
    //    AfterSalePO afterSale = OperationalJudgment.judgment(afterSaleCommandService.getAfterSaleBySn(sn));
    //    return Result.success(AfterSaleAssembler.INSTANCE.convert(afterSale));
    //}
	//
    //@Operation(summary = "分页获取售后服务", description = "分页获取售后服务")
    //@RequestLogger
    //@PreAuthorize("hasAuthority('dept:tree:data')")
    //@GetMapping(value = "/page")
    //public Result<PageResult<AfterSaleCO>> pageQuery(
    //        @Validated AfterSalePageQry afterSalePageQry) {
    //    IPage<AfterSalePO> afterSalePages = afterSaleCommandService.pageQuery(afterSalePageQry);
    //    return Result.success(MpUtils.convertMybatisPage(afterSalePages, AfterSaleCO.class));
    //}
	//
    //@Operation(summary = "获取申请售后页面信息", description = "获取申请售后页面信息")
    //@RequestLogger
    //@PreAuthorize("hasAuthority('dept:tree:data')")
    //@GetMapping(value = "/apply-aftersale-info/{sn}")
    //public Result<AfterSaleApplyCO> applyAfterSaleInfo(
    //        @NotBlank(message = "售后单号不能为空") @PathVariable String sn) {
    //    return Result.success(afterSaleCommandService.getAfterSaleVO(sn));
    //}
	//
    //@Operation(summary = "申请售后", description = "申请售后")
    //@RequestLogger
    //@PreAuthorize("hasAuthority('dept:tree:data')")
    //@PostMapping(value = "/{orderItemSn}")
    //public Result<Boolean> saveAfterSale(
    //        @NotBlank(message = "售后单号不能为空") @PathVariable String orderItemSn,
    //        @Validated @RequestBody AfterSaleAddCmd afterSaleAddCmd) {
    //    afterSaleAddCmd = AfterSaleDTOBuilder.builder(afterSaleAddCmd)
    //            .orderItemSn(orderItemSn)
    //            .build();
    //    return Result.success(afterSaleCommandService.saveAfterSale(afterSaleAddCmd));
    //}
	//
    //@Operation(summary = "买家 退回 物流信息", description = "买家 退回 物流信息")
    //@RequestLogger
    //@PreAuthorize("hasAuthority('dept:tree:data')")
    //@PostMapping(value = "/delivery/{afterSaleSn}")
    //public Result<AfterSalePO> delivery(
    //        @NotNull(message = "售后编号不能为空") @PathVariable("afterSaleSn") String afterSaleSn,
    //        @NotNull(message = "发货单号不能为空") @RequestParam String logisticsNo,
    //        @NotNull(message = "请选择物流公司") @RequestParam String logisticsId,
    //        @NotNull(message = "请选择发货时间") @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")
    //        LocalDateTime mDeliverTime) {
    //    return Result.success(
    //            afterSaleCommandService.buyerDelivery(afterSaleSn, logisticsNo, logisticsId, mDeliverTime));
    //}
	//
    //@Operation(summary = "售后，取消售后", description = "售后，取消售后")
    //@RequestLogger
    //@PreAuthorize("hasAuthority('dept:tree:data')")
    //@PostMapping(value = "/cancel/{afterSaleSn}")
    //public Result<Boolean> cancel(
    //        @NotNull(message = "售后订单编码不能为空") @PathVariable String afterSaleSn) {
    //    return Result.success(afterSaleCommandService.cancel(afterSaleSn));
    //}
	//
    //@Operation(summary = "获取商家售后收件地址", description = "获取商家售后收件地址")
    //@RequestLogger
    //@PreAuthorize("hasAuthority('dept:tree:data')")
    //@GetMapping(value = "/getStoreAfterSaleAddress/{sn}")
    //public Result<StoreAfterSaleAddressVO> getStoreAfterSaleAddress(
    //        @NotNull(message = "售后单号") @PathVariable("sn") String sn) {
    //    return Result.success(afterSaleCommandService.getStoreAfterSaleAddressVO(sn));
    //}
	//
    //@Operation(summary = "获取售后原因", description = "获取售后原因")
    //@RequestLogger
    //@PreAuthorize("hasAuthority('dept:tree:data')")
    //@GetMapping(value = "/afterSaleReason/{serviceType}")
    //public Result<List<AfterSaleReasonCO>> getAfterSaleReason(
    //        @NotBlank(message = "售后类型不能为空") @PathVariable String serviceType) {
    //    List<AfterSaleReasonPO> afterSaleReasonPOS = afterSaleReasonCommandService.afterSaleReasonList(
    //            serviceType);
    //    return Result.success(AfterSaleReasonAssembler.INSTANCE.convert(afterSaleReasonPOS));
    //}
	//
    //@Operation(summary = "获取售后日志", description = "获取售后日志")
    //@RequestLogger
    //@PreAuthorize("hasAuthority('dept:tree:data')")
    //@GetMapping(value = "/afterSaleLog/{sn}")
    //public Result<List<AfterSaleLogCO>> getAfterSaleLog(
    //        @NotBlank(message = "售后单号不能为空") @PathVariable String sn) {
    //    List<AfterSaleLogPO> afterSaleLogPOList = afterSaleLogCommandService.getAfterSaleLog(sn);
    //    return Result.success(AfterSaleLogAssembler.INSTANCE.convert(afterSaleLogPOList));
    //}
}
