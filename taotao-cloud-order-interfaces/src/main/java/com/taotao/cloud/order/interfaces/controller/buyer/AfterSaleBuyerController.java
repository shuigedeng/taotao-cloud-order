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
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.web.utils.OperationalJudgment;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.order.application.dto.aftersale.command.AfterSaleAddCommand;
import com.taotao.cloud.order.application.dto.aftersale.command.AfterSaleAddCommandBuilder;
import com.taotao.cloud.order.application.dto.aftersale.query.AfterSalePageQuery;
import com.taotao.cloud.order.application.dto.aftersale.query.SnQuery;
import com.taotao.cloud.order.application.dto.aftersale.result.AfterSaleApplyResult;
import com.taotao.cloud.order.application.dto.aftersale.result.AfterSaleLogResult;
import com.taotao.cloud.order.application.dto.aftersale.result.AfterSaleReasonResult;
import com.taotao.cloud.order.application.dto.aftersale.result.AfterSaleResult;
import com.taotao.cloud.order.application.dto.aftersale.result.StoreAfterSaleAddressResult;
import com.taotao.cloud.order.application.service.command.AfterSaleCommandService;
import com.taotao.cloud.order.application.service.query.AfterSaleLogQueryService;
import com.taotao.cloud.order.application.service.query.AfterSaleQueryService;
import com.taotao.cloud.order.application.service.query.AfterSaleReasonQueryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 买家端,售后管理API
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-07 20:31:22
 */
@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "买家端-售后API", description = "买家端-售后API")
@RequestMapping("/buyer/order/aftersale")
public class AfterSaleBuyerController extends BusinessController {

	private final AfterSaleCommandService afterSaleCommandService;
	private final AfterSaleQueryService afterSaleQueryService;
	private final AfterSaleReasonQueryService afterSaleReasonQueryService;
	private final AfterSaleLogQueryService afterSaleLogQueryService;

	@Operation(summary = "查看售后服务详情", description = "查看售后服务详情")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/sn")
	public Result<AfterSaleResult> queryAfterSaleBySn( SnQuery snQuery ) {
		AfterSaleResult afterSale =
			OperationalJudgment.judgment(afterSaleQueryService.queryAfterSaleBySn(snQuery.getSn()));
		return Result.success(afterSale);
	}

	@Operation(summary = "分页获取售后服务", description = "分页获取售后服务")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/page")
	public Result<PageResult<AfterSaleResult>> pageQuery(
			@Validated AfterSalePageQuery afterSalePageQry) {
		return Result.success(afterSaleQueryService.pageQuery(afterSalePageQry));
	}

	@Operation(summary = "获取申请售后页面信息", description = "获取申请售后页面信息")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/apply-aftersale-info/{sn}")
	public Result<AfterSaleApplyResult> applyAfterSaleInfo(
			@NotBlank(message = "售后单号不能为空") @PathVariable String sn) {
		return Result.success(afterSaleQueryService.queryAfterSaleApply(sn));
	}

	@Operation(summary = "申请售后", description = "申请售后")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/{orderItemSn}")
	public Result<Void> saveAfterSale(
			@NotBlank(message = "售后单号不能为空") @PathVariable String orderItemSn,
			@Validated @RequestBody AfterSaleAddCommand afterSaleAddCmd) {
		AfterSaleAddCommand cmd = AfterSaleAddCommandBuilder.builder(afterSaleAddCmd)
				.orderItemSn(orderItemSn)
				.build();
		afterSaleCommandService.saveAfterSale(cmd);
		return Result.success();
	}

	@Operation(summary = "买家 退回 物流信息", description = "买家 退回 物流信息")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/delivery/{afterSaleSn}")
	public Result<AfterSaleResult> delivery(
			@NotNull(message = "售后编号不能为空") @PathVariable("afterSaleSn") String afterSaleSn,
			@NotNull(message = "发货单号不能为空") @RequestParam String logisticsNo,
			@NotNull(message = "请选择物流公司") @RequestParam String logisticsId,
			@NotNull(message = "请选择发货时间") @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")
			LocalDateTime mDeliverTime) {
		return Result.success(
				afterSaleCommandService.buyerDelivery(afterSaleSn, logisticsNo, logisticsId,
						mDeliverTime));
	}

	@Operation(summary = "售后，取消售后", description = "售后，取消售后")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/cancel/{afterSaleSn}")
	public Result<Void> cancel(
			@NotNull(message = "售后订单编码不能为空") @PathVariable String afterSaleSn) {
		afterSaleCommandService.cancel(afterSaleSn);
		return Result.success();
	}

	@Operation(summary = "获取商家售后收件地址", description = "获取商家售后收件地址")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/queryStoreAfterSaleAddress/{sn}")
	public Result<StoreAfterSaleAddressResult> queryStoreAfterSaleAddress(
			@NotNull(message = "售后单号") @PathVariable("sn") String sn) {
		return Result.success(afterSaleQueryService.queryStoreAfterSaleAddress(sn));
	}

	@Operation(summary = "获取售后原因", description = "获取售后原因")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/afterSaleReason/{serviceType}")
	public Result<List<AfterSaleReasonResult>> queryAfterSaleReason(
			@NotBlank(message = "售后类型不能为空") @PathVariable String serviceType) {
		List<AfterSaleReasonResult> afterSaleReasonResults =
				afterSaleReasonQueryService.afterSaleReasonList(serviceType);
		return Result.success(AfterSaleReasonAssembler.INSTANCE.convert(afterSaleReasonResults));
	}

	@Operation(summary = "获取售后日志", description = "获取售后日志")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/afterSaleLog/{sn}")
	public Result<List<AfterSaleLogResult>> queryAfterSaleLog(
			@NotBlank(message = "售后单号不能为空") @PathVariable String sn) {
		List<AfterSaleLogResult> afterSaleLogResultList = afterSaleLogQueryService.queryAfterSaleLog(sn);
		return Result.success(AfterSaleLogAssembler.INSTANCE.convert(afterSaleLogResultList));
	}
}
