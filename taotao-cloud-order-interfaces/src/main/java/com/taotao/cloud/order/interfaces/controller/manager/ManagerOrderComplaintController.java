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
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理端,交易投诉API
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:57:14
 */
@AllArgsConstructor
@Validated
@RestController
@Tag(name = "管理端-交易投诉管理API", description = "管理端-交易投诉管理API")
@RequestMapping("/order/manager/order/complain")
public class ManagerOrderComplaintController extends BusinessController {

	///**
	// * 交易投诉
	// */
	//private final OrderComplaintCommandService orderComplaintCommandService;
	//
	///**
	// * 交易投诉沟通
	// */
	//private final OrderComplaintCommunicationCommandService orderComplaintCommunicationCommandService;
	//
	//@Operation(summary = "通过id获取", description = "通过id获取")
	//@RequestLogger
	//@PreAuthorize("hasAuthority('dept:tree:data')")
	//@GetMapping(value = "/{id}")
	//public Result<OrderComplaintCO> get(@PathVariable Long id) {
	//	return Result.success(orderComplaintCommandService.getOrderComplainById(id));
	//}
	//
	//@Operation(summary = "分页获取", description = "分页获取")
	//@RequestLogger
	//@PreAuthorize("hasAuthority('dept:tree:data')")
	//@GetMapping("/page")
	//public Result<PageResult<OrderComplaintBaseCO>> pageQuery(
	//	@Validated OrderComplaintPageQry orderComplaintPageQry) {
	//	IPage<OrderComplaintPO> page = orderComplaintCommandService.pageQuery(orderComplaintPageQry);
	//	return Result.success(MpUtils.convertMybatisPage(page, OrderComplaintBaseCO.class));
	//}
	//
	//@Operation(summary = "更新数据", description = "更新数据")
	//@RequestLogger
	//@PreAuthorize("hasAuthority('dept:tree:data')")
	//@PutMapping("/{id}")
	//public Result<Boolean> update(@PathVariable Long id,
	//							  @Validated @RequestBody OrderComplaintAddCmd orderComplaintAddCmd) {
	//	OrderComplaintPO orderComplaintPO = OrderComplainAssembler.INSTANCE.convert(
	//		orderComplaintAddCmd);
	//	orderComplaintPO.setId(id);
	//	return Result.success(orderComplaintCommandService.updateOrderComplain(orderComplaintPO));
	//}
	//
	//@Operation(summary = "添加交易投诉对话", description = "添加交易投诉对话")
	//@RequestLogger
	//@PreAuthorize("hasAuthority('dept:tree:data')")
	//@PostMapping("/communication/{complainId}")
	//public Result<Boolean> addCommunication(
	//	@PathVariable("complainId") Long complainId,
	//	@Validated @RequestBody OrderComplaintCommunicationAddCmd orderComplaintCommunicationAddCmd) {
	//	SecurityUser user = SecurityUtils.getCurrentUser();
	//	OrderComplaintCommunicationPO orderComplaintCommunicationPO = OrderComplaintCommunicationPO.builder()
	//		.complainId(complainId)
	//		.content(orderComplaintCommunicationAddCmd.content())
	//		.owner(CommunicationOwnerEnum.PLATFORM.name())
	//		.ownerName(user.getUsername())
	//		.ownerId(user.getUserId())
	//		.build();
	//	return Result.success(
	//		orderComplaintCommunicationCommandService.addCommunication(orderComplaintCommunicationPO));
	//}
	//
	//@Operation(summary = "修改状态", description = "修改状态")
	//@RequestLogger
	//@PreAuthorize("hasAuthority('dept:tree:data')")
	//@PutMapping(value = "/status")
	//public Result<Boolean> updateStatus(
	//	@Validated @RequestBody OrderComplaintOperationAddCmd orderComplaintOperationAddCmd) {
	//	return Result.success(
	//		orderComplaintCommandService.updateOrderComplainByStatus(orderComplaintOperationAddCmd));
	//}
	//
	//@Operation(summary = "仲裁", description = "仲裁")
	//@RequestLogger
	//@PreAuthorize("hasAuthority('dept:tree:data')")
	//@PutMapping(value = "/complete/{id}")
	//public Result<Boolean> complete(@PathVariable Long id, String arbitrationResult) {
	//	// 新建对象
	//	OrderComplaintOperationAddCmd orderComplaintOperationAddCmd = OrderComplaintOperationDTOBuilder.builder()
	//		.complainId(id)
	//		.arbitrationResult(arbitrationResult)
	//		.complainStatus(OrderComplaintStatusEnum.COMPLETE.name())
	//		.build();
	//
	//	// 修改状态
	//	return Result.success(
	//		orderComplaintCommandService.updateOrderComplainByStatus(orderComplaintOperationAddCmd));
	//}
}
