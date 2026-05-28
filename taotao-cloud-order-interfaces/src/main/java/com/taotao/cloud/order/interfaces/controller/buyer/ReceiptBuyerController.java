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

import com.taotao.boot.common.model.result.PageResult;
import com.taotao.boot.common.model.result.Result;
import com.taotao.boot.data.mybatis.mybatisplus.MpUtils;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.order.application.dto.order.command.CreateOrderReceiptCommand;
import com.taotao.cloud.order.application.dto.order.query.ReceiptPageQuery;
import com.taotao.cloud.order.application.dto.order.result.ReceiptResult;
import com.taotao.cloud.order.application.service.command.ReceiptCommandService;
import com.taotao.cloud.order.application.service.query.ReceiptQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "买家端-发票API", description = "买家端-发票API")
@RequestMapping("/buyer/order/receipt")
public class ReceiptBuyerController extends BusinessController {

	private final ReceiptQueryService receiptQueryService;
	private final ReceiptCommandService receiptCommandService;

	@Operation(summary = "获取发票详情", description = "获取发票详情")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping("/{id}")
	public Result<ReceiptResult> queryDetail(@PathVariable Long id) {
		return Result.success(receiptQueryService.queryDetail(id));
	}

	@Operation(summary = "获取发票分页信息", description = "获取发票分页信息")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping("/page")
	public Result<PageResult<CreateOrderReceiptCommand>> queryPage(ReceiptPageQuery receiptPageQuery) {
		return Result.success(MpUtils.convertMpPage(receiptQueryService.pageQuery(receiptPageQuery), CreateOrderReceiptCommand.class));
	}

	@Operation(summary = "保存发票信息", description = "保存发票信息")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping
	public Result<Void> save(@Valid ReceiptResult receipt) {
		receiptCommandService.saveReceipt(receipt);
		return Result.success();
	}
}
