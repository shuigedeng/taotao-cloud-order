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
import com.taotao.cloud.order.application.dto.order.cmmond.OrderReceiptAddCmd;
import com.taotao.cloud.order.application.dto.order.query.ReceiptPageQry;
import com.taotao.cloud.order.application.service.order.OrderCommandService;
import com.taotao.cloud.order.application.service.order.ReceiptCommandService;
import com.taotao.cloud.order.infrastructure.persistent.persistence.order.ReceiptPO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.taotao.boot.webagg.controller.BusinessController;

/**
 * 店铺端,发票API
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:57:45
 */
@AllArgsConstructor
@Validated
@RestController
@Tag(name = "店铺端-发票API", description = "店铺端-发票API")
@RequestMapping("/order/seller/receipt")
public class SellerReceiptController extends BusinessController {

	private final ReceiptCommandService receiptCommandService;

	private final OrderCommandService orderCommandService;

	@Operation(summary = "分页获取", description = "分页获取")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping("/page")
	public Result<PageResult<OrderReceiptAddCmd>> getByPage(ReceiptPageQry receiptPageQry) {
		receiptPageQry.storeId(SecurityUtils.getCurrentUser().getStoreId());
		IPage<OrderReceiptAddCmd> page = receiptCommandService.pageQuery(receiptPageQry);
		return Result.success(MpUtils.convertMybatisPage(page, OrderReceiptAddCmd.class));
	}

	@Operation(summary = "通过id获取", description = "通过id获取")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/{id}")
	public Result<ReceiptPO> get(@PathVariable String id) {
		return Result.success(OperationalJudgment.judgment(receiptCommandService.getById(id)));
	}

	@Operation(summary = "开发票", description = "开发票")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/{id}/invoicing")
	public Result<ReceiptPO> invoicing(@PathVariable Long id) {
		OperationalJudgment.judgment(receiptCommandService.getById(id));
		return Result.success(receiptCommandService.invoicing(id));
	}

	@Operation(summary = "通过订单编号获取", description = "通过订单编号获取")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/orderSn/{orderSn}")
	public Result<ReceiptPO> getByOrderSn(@PathVariable String orderSn) {
		OperationalJudgment.judgment(orderCommandService.getBySn(orderSn));
		return Result.success(receiptCommandService.getByOrderSn(orderSn));
	}
}
