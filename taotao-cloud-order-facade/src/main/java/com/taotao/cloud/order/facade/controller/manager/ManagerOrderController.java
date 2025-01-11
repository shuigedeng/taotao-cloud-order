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

package com.taotao.cloud.order.facade.controller.manager;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理端,订单API
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:57:16
 */
@AllArgsConstructor
@Validated
@RestController
@Tag(name = "管理端-订单管理API", description = "管理端-订单管理API")
@RequestMapping("/order/manager/order")
public class ManagerOrderController {

	///**
	// * 订单
	// */
	//private final OrderCommandService orderCommandService;
	///**
	// * 订单价格
	// */
	//private final OrderPriceCommandService orderPriceCommandService;
	//
	//@Operation(summary = "查询订单列表分页", description = "查询订单列表分页")
	//@RequestLogger
	//@PreAuthorize("hasAuthority('dept:tree:data')")
	//@GetMapping("/tree")
	//public Result<PageResult<OrderSimpleCO>> queryMineOrder(OrderPageQry orderPageQry) {
	//	IPage<OrderSimpleCO> page = orderCommandService.pageQuery(orderPageQry);
	//	return Result.success(MpUtils.convertMybatisPage(page, OrderSimpleCO.class));
	//}
	//
	//@Operation(summary = "查询订单导出列表", description = "查询订单导出列表")
	//@RequestLogger
	//@PreAuthorize("hasAuthority('dept:tree:data')")
	//@GetMapping("/queryExportOrder")
	//public Result<List<OrderExportCO>> queryExportOrder(OrderPageQry orderPageQry) {
	//	return Result.success(orderCommandService.queryExportOrder(orderPageQry));
	//}
	//
	//@Operation(summary = "订单明细", description = "订单明细")
	//@RequestLogger
	//@PreAuthorize("hasAuthority('dept:tree:data')")
	//@GetMapping(value = "/{orderSn}")
	//public Result<OrderDetailCO> detail(@PathVariable String orderSn) {
	//	return Result.success(orderCommandService.queryDetail(orderSn));
	//}
	//
	//@Operation(summary = "确认收款", description = "确认收款")
	//@RequestLogger
	//@PreAuthorize("hasAuthority('dept:tree:data')")
	//@PostMapping(value = "/{orderSn}/pay")
	//public Result<Boolean> payOrder(@PathVariable String orderSn) {
	//	return Result.success(orderPriceCommandService.adminPayOrder(orderSn));
	//}
	//
	//@Operation(summary = "修改收货人信息", description = "修改收货人信息")
	//@RequestLogger
	//@PreAuthorize("hasAuthority('dept:tree:data')")
	//@PutMapping(value = "/{orderSn}/consignee")
	//public Result<Order> consignee(
	//	@NotNull(message = "参数非法") @PathVariable String orderSn,
	//	@Valid TradeAddCmd.MemberAddressDTO memberAddressDTO) {
	//	return Result.success(orderCommandService.updateConsignee(orderSn, memberAddressDTO));
	//}
	//
	//@Operation(summary = "修改订单价格", description = "修改订单价格")
	//@RequestLogger
	//@PutMapping(value = "/{orderSn}/price")
	//public Result<Boolean> updateOrderPrice(
	//	@PathVariable String orderSn,
	//	@NotNull(message = "订单价格不能为空") @RequestParam BigDecimal price) {
	//	return Result.success(orderPriceCommandService.updatePrice(orderSn, price));
	//}
	//
	//@Operation(summary = "取消订单", description = "取消订单")
	//@RequestLogger
	//@PreAuthorize("hasAuthority('dept:tree:data')")
	//@PostMapping(value = "/{orderSn}/cancel")
	//public Result<Order> cancel(@PathVariable String orderSn, @RequestParam String reason) {
	//	return Result.success(orderCommandService.cancel(orderSn, reason));
	//}
	//
	//@Operation(summary = "查询物流踪迹", description = "查询物流踪迹")
	//@RequestLogger
	//@PreAuthorize("hasAuthority('dept:tree:data')")
	//@PostMapping(value = "/traces/{orderSn}")
	//public Result<Traces> getTraces(
	//	@NotBlank(message = "订单编号不能为空") @PathVariable String orderSn) {
	//	return Result.success(orderCommandService.getTraces(orderSn));
	//}
}
