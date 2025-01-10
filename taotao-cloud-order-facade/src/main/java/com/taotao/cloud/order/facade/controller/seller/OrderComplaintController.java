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

package com.taotao.cloud.order.facade.controller.seller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.boot.common.model.PageResult;
import com.taotao.boot.common.model.Result;
import com.taotao.boot.data.mybatis.mybatisplus.MpUtils;
import com.taotao.boot.security.spring.utils.SecurityUtils;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.web.utils.OperationalJudgment;
import com.taotao.cloud.order.api.enums.order.CommunicationOwnerEnum;
import com.taotao.cloud.order.application.assembler.OrderComplainAssembler;
import com.taotao.cloud.order.application.dto.order.clientobject.OrderComplaintBaseCO;
import com.taotao.cloud.order.application.dto.order.clientobject.OrderComplaintCO;
import com.taotao.cloud.order.application.dto.order.cmmond.OrderComplaintAddCmd;
import com.taotao.cloud.order.application.dto.order.cmmond.OrderComplaintCommunicationAddCmd;
import com.taotao.cloud.order.application.dto.order.cmmond.OrderComplaintOperationAddCmd;
import com.taotao.cloud.order.application.dto.order.cmmond.StoreAppealCmd;
import com.taotao.cloud.order.application.dto.order.query.OrderComplaintPageQry;
import com.taotao.cloud.order.application.service.order.OrderComplaintCommunicationCommandService;
import com.taotao.cloud.order.application.service.order.OrderComplaintCommandService;
import com.taotao.cloud.order.infrastructure.persistent.persistence.order.OrderComplaintCommunicationPO;
import com.taotao.cloud.order.infrastructure.persistent.persistence.order.OrderComplaintPO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 店铺端,交易投诉API
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:57:35
 */
@AllArgsConstructor
@Validated
@RestController
@Tag(name = "店铺端-交易投诉API", description = "店铺端-交易投诉API")
@RequestMapping("/order/seller/order/complain")
public class OrderComplaintController {

	///**
	// * 交易投诉
	// */
	//private final OrderComplaintCommandService orderComplaintCommandService;
	//
	///**
	// * 投诉沟通
	// */
	//private final OrderComplaintCommunicationCommandService orderComplaintCommunicationCommandService;
	//
	//@Operation(summary = "通过id获取", description = "通过id获取")
	//@RequestLogger
	//@PreAuthorize("hasAuthority('dept:tree:data')")
	//@GetMapping(value = "/{id}")
	//public Result<OrderComplaintCO> getOrderComplainById(@PathVariable Long id) {
	//	return Result.success(
	//		OperationalJudgment.judgment(orderComplaintCommandService.getOrderComplainById(id)));
	//}
	//
	//@Operation(summary = "分页获取", description = "分页获取")
	//@RequestLogger
	//@PreAuthorize("hasAuthority('dept:tree:data')")
	//@GetMapping("/page")
	//public Result<PageResult<OrderComplaintBaseCO>> get(
	//	OrderComplaintPageQry orderComplaintPageQry) {
	//	Long storeId = SecurityUtils.getCurrentUser().getStoreId();
	//	orderComplaintPageQry.setStoreId(storeId);
	//	IPage<OrderComplaintPO> page = orderComplaintCommandService.pageQuery(orderComplaintPageQry);
	//	return Result.success(MpUtils.convertMybatisPage(page, OrderComplaintBaseCO.class));
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
	//		.owner(CommunicationOwnerEnum.STORE.name())
	//		.ownerName(user.getUsername())
	//		.ownerId(user.getStoreId())
	//		.build();
	//	return Result.success(
	//		orderComplaintCommunicationCommandService.addCommunication(orderComplaintCommunicationPO));
	//}
	//
	//@Operation(summary = "修改申诉信息", description = "修改申诉信息")
	//@RequestLogger
	//@PreAuthorize("hasAuthority('dept:tree:data')")
	//@PutMapping("/{id}")
	//public Result<Boolean> update(@PathVariable Long id,
	//							  @Validated @RequestBody OrderComplaintAddCmd orderComplaintAddCmd) {
	//	Long storeId = SecurityUtils.getCurrentUser().getStoreId();
	//	OrderComplaintPO orderComplaintPO = OrderComplainAssembler.INSTANCE.convert(
	//		orderComplaintAddCmd);
	//	orderComplaintPO.setStoreId(storeId);
	//	return Result.success(orderComplaintCommandService.updateOrderComplain(orderComplaintPO));
	//}
	//
	//@Operation(summary = "申诉", description = "申诉")
	//@RequestLogger
	//@PreAuthorize("hasAuthority('dept:tree:data')")
	//@PostMapping("/appeal")
	//public Result<OrderComplaintCO> appeal(@Validated @RequestBody StoreAppealCmd storeAppealDTO) {
	//	orderComplaintCommandService.appeal(storeAppealDTO);
	//	return Result.success(
	//		orderComplaintCommandService.getOrderComplainById(storeAppealDTO.orderComplaintId()));
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
}
