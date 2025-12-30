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
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 买家端,交易投诉API
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:56:57
 */
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "买家端-交易投诉API", description = "买家端-交易投诉API")
@RequestMapping("/order/buyer/order/complain")
public class BuyerOrderComplaintController extends BusinessController {
    //
    /// **
    // * 交易投诉
    // */
    // private final OrderComplaintCommandService orderComplaintCommandService;
    //
    /// **
    // * 交易投诉沟通
    // */
    // private final OrderComplaintCommunicationCommandService
    // orderComplaintCommunicationCommandService;
    //
    // @Operation(summary = "通过id获取", description = "通过id获取")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @GetMapping(value = "/{id}")
    // public Result<OrderComplaintCO> get(@PathVariable Long id) {
    //	OrderComplaintCO orderComplaintCO =
    //		OperationalJudgment.judgment(orderComplaintCommandService.getOrderComplainById(id));
    //	return Result.success(orderComplaintCO);
    // }
    //
    // @Operation(summary = "分页获取", description = "分页获取")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @GetMapping("/page")
    // public Result<PageResult<OrderComplaintBaseCO>> get(
    //	@Validated OrderComplaintPageQry orderComplaintPageQry) {
    //	IPage<OrderComplaintPO> orderComplainByPage = orderComplaintCommandService.pageQuery(
    //		orderComplaintPageQry);
    //	return Result.success(
    //		MpUtils.convertMybatisPage(orderComplainByPage, OrderComplaintBaseCO.class));
    // }
    //
    // @Operation(summary = "添加交易投诉", description = "添加交易投诉")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @PostMapping
    // public Result<OrderComplaintPO> add(@Valid OrderComplaintAddCmd orderComplaintAddCmd) {
    //	return Result.success(orderComplaintCommandService.addOrderComplain(orderComplaintAddCmd));
    // }
    //
    // @Operation(summary = "添加交易投诉对话", description = "添加交易投诉对话")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @PostMapping("/communication/{complainId}")
    // public Result<Boolean> addCommunication(
    //	@PathVariable("complainId") Long complainId,
    //	@Validated @RequestBody OrderComplaintCommunicationAddCmd orderComplaintCommunicationAddCmd)
    // {
    //	SecurityUser user = SecurityUtils.getCurrentUser();
    //	OrderComplaintCommunicationPO orderComplaintCommunicationPO =
    // OrderComplaintCommunicationPO.builder()
    //		.complainId(complainId)
    //		.content(orderComplaintCommunicationAddCmd.content())
    //		.owner(CommunicationOwnerEnum.BUYER.name())
    //		.ownerName(user.getNickname())
    //		.ownerId(user.getUserId())
    //		.build();
    //
    //	return Result.success(
    //		orderComplaintCommunicationCommandService.addCommunication(orderComplaintCommunicationPO));
    // }
    //
    // @Operation(summary = "取消售后", description = "取消售后")
    // @RequestLogger
    // @PreAuthorize("hasAuthority('dept:tree:data')")
    // @PutMapping(value = "/status/{id}")
    // public Result<Boolean> cancel(@PathVariable Long id) {
    //	return Result.success(orderComplaintCommandService.cancel(id));
    // }
}
