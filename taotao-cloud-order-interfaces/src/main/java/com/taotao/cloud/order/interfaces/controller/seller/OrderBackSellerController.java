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

import com.taotao.boot.webagg.controller.BusinessController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 店铺端,订单API
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:57:38
 */
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "店铺端-订单API", description = "店铺端-订单API")
@RequestMapping("/order/seller/order")
public class OrderBackSellerController extends BusinessController {
    //
    /// **
    // * 订单
    // */
    // private final OrderCommandService orderCommandService;
    /// **
    // * 订单价格
    // */
    // private final OrderPriceCommandService orderPriceCommandService;
    /// **
    // * 物流公司
    // */
    // private final FeignStoreLogisticsApi storeLogisticsService;
    //
    // @Operation(summary = "查询订单列表", description = "查询订单列表")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @GetMapping("/page")
    // public Result<PageResult<OrderSimpleCO>> queryMineOrder(OrderPageQry orderPageQry) {
    //	IPage<OrderSimpleCO> page = orderCommandService.pageQuery(orderPageQry);
    //	return Result.success(MpUtils.convertMybatisPage(page, OrderSimpleCO.class));
    // }
    //
    // @Operation(summary = "订单明细", description = "订单明细")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @GetMapping(value = "/{orderSn}")
    // public Result<OrderDetailCO> getBySn(@NotNull @PathVariable String orderSn) {
    //	OperationalJudgment.judgment(orderCommandService.getBySn(orderSn));
    //	return Result.success(orderCommandService.queryDetail(orderSn));
    // }
    //
    // @Operation(summary = "修改收货人信息", description = "修改收货人信息")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @PostMapping(value = "/update/{orderSn}/consignee")
    // public Result<Order> consignee(
    //	@NotNull(message = "参数非法") @PathVariable String orderSn,
    //	@Valid TradeAddCmd.MemberAddressDTO memberAddressDTO) {
    //	return Result.success(orderCommandService.updateConsignee(orderSn, memberAddressDTO));
    // }
    //
    // @Operation(summary = "修改订单价格", description = "修改订单价格")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @PostMapping(value = "/{orderSn}/price")
    // public Result<Boolean> updateOrderPrice(
    //	@PathVariable String orderSn,
    //	@NotNull(message = "订单价格不能为空") @RequestParam BigDecimal orderPrice) {
    //	return Result.success(orderPriceCommandService.updatePrice(orderSn, orderPrice));
    // }
    //
    // @Operation(summary = "订单发货", description = "订单发货")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @PostMapping(value = "/{orderSn}/delivery")
    // public Result<Order> delivery(
    //	@NotNull(message = "参数非法") @PathVariable String orderSn,
    //	@NotNull(message = "发货单号不能为空") String logisticsNo,
    //	@NotNull(message = "请选择物流公司") Long logisticsId) {
    //	return Result.success(orderCommandService.delivery(orderSn, logisticsNo, logisticsId));
    // }
    //
    // @Operation(summary = "取消订单", description = "取消订单")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @PostMapping(value = "/{orderSn}/cancel")
    // public Result<Order> cancel(@PathVariable String orderSn, @RequestParam String reason) {
    //	return Result.success(orderCommandService.cancel(orderSn, reason));
    // }
    //
    // @Operation(summary = "根据核验码获取订单信息", description = "根据核验码获取订单信息")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @GetMapping(value = "/verificationCode/{verificationCode}")
    // public Result<Order> getOrderByVerificationCode(@PathVariable String verificationCode) {
    //	return Result.success(orderCommandService.getOrderByVerificationCode(verificationCode));
    // }
    //
    // @Operation(summary = "订单核验", description = "订单核验")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @PostMapping(value = "/take/{orderSn}/{verificationCode}")
    // public Result<Order> take(@PathVariable String orderSn, @PathVariable String
    // verificationCode) {
    //	return Result.success(orderCommandService.take(orderSn, verificationCode));
    // }
    //
    // @Operation(summary = "查询物流踪迹", description = "查询物流踪迹")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @GetMapping(value = "/traces/{orderSn}")
    // public Result<Traces> getTraces(
    //	@NotBlank(message = "订单编号不能为空") @PathVariable String orderSn) {
    //	OperationalJudgment.judgment(orderCommandService.getBySn(orderSn));
    //	return Result.success(orderCommandService.getTraces(orderSn));
    // }
    //
    // @Operation(summary = "下载待发货的订单列表", description = "下载待发货的订单列表")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @GetMapping(value = "/downLoadDeliverExcel")
    // public void downLoadDeliverExcel() {
    //	// 获取店铺已经选择物流公司列表
    //	List<String> logisticsName = storeLogisticsService.getStoreSelectedLogisticsName(
    //		SecurityUtils.getCurrentUser().getStoreId());
    //	// 下载订单批量发货Excel
    //	this.orderCommandService.downLoadDeliver(RequestUtils.getResponse(), logisticsName);
    // }
    //
    // @Operation(summary = "上传文件进行订单批量发货", description = "上传文件进行订单批量发货")
    // @RequestLogger
    // @PostMapping(value = "/batchDeliver", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // public Result<Boolean> batchDeliver(@RequestPart("files") MultipartFile files) {
    //	return Result.success(orderCommandService.batchDeliver(files));
    // }
    //
    // @Operation(summary = "查询订单导出列表", description = "查询订单导出列表")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @GetMapping("/queryExportOrder")
    // public Result<List<OrderExportCO>> queryExportOrder(OrderPageQry orderPageQry) {
    //	return Result.success(orderCommandService.queryExportOrder(orderPageQry));
    // }
}
