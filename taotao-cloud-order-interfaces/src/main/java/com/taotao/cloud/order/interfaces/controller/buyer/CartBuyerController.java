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

import com.taotao.boot.common.model.result.Result;
import com.taotao.boot.web.request.annotation.RequestLogger;
import com.taotao.boot.webagg.controller.BusinessController;
import com.taotao.cloud.order.application.dto.cart.TradeDTO;
import com.taotao.cloud.order.application.dto.order.result.ReceiptResult;
import com.taotao.cloud.order.application.service.command.CartCommandService;
import com.taotao.cloud.order.application.service.query.CartQueryService;
import com.taotao.cloud.order.common.enums.cart.CartTypeEnum;
import com.taotao.cloud.order.application.dto.trade.result.TradeResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Validated
@RestController
@Tag(name = "买家端-购物车API", description = "买家端-购物车API")
@RequestMapping("/buyer/order/cart")
public class CartBuyerController extends BusinessController {

	private final CartQueryService cartQueryService;
	private final CartCommandService cartCommandService;

	@Operation(summary = "向购物车中添加一个产品", description = "向购物车中添加一个产品")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping
	public Result<Void> add(
			@NotNull(message = "产品id不能为空") String skuId,
			@NotNull(message = "购买数量不能为空") @Min(value = 1, message = "加入购物车数量必须大于0") Integer num,
			@NotBlank(message = "购物车类型") String cartType) {
		cartCommandService.add(skuId, num, cartType, false);
		return Result.success();
	}

	@Operation(summary = "获取购物车页面购物车详情", description = "获取购物车页面购物车详情")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping("/all")
	public Result<TradeDTO> cartAll() {
		return Result.success(this.cartQueryService.queryAllTradeDTO());
	}

	@Operation(summary = "获取购物车数量", description = "获取购物车数量")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping("/count")
	public Result<Long> cartCount(@RequestParam(required = false) Boolean checked) {
		return Result.success(this.cartQueryService.queryCartNum(checked));
	}

	@Operation(summary = "获取购物车可用优惠券数量", description = "获取购物车可用优惠券数量")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping("/coupon/num")
	public Result<Long> cartCouponNum(@RequestParam String way) {
		return Result.success(this.cartQueryService.queryCanUseCoupon(CartTypeEnum.valueOf(way)));
	}

	@Operation(summary = "更新购物车中的多个产品的数量或选中状态", description = "更新购物车中的多个产品的数量或选中状态")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/sku/num/{skuId}")
	public Result<Void> update(
			@NotNull(message = "产品id不能为空") @PathVariable(name = "skuId") String skuId, Integer num) {
		cartCommandService.add(skuId, num, CartTypeEnum.CART.name(), true);
		return Result.success();
	}

	@Operation(summary = "更新购物车中单个产品 更新购物车中的多个产品的数量或选中状态", description = "更新购物车中的多个产品的数量或选中状态")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/sku/checked/{skuId}")
	public Result<Void> updateChecked(
			@NotNull(message = "产品id不能为空") @PathVariable(name = "skuId") String skuId, boolean checked) {
		cartCommandService.checked(skuId, checked);
		return Result.success();
	}

	@Operation(summary = "购物车选中设置", description = "购物车选中设置")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/sku/checked", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Void> updateAll(boolean checked) {
		cartCommandService.checkedAll(checked);
		return Result.success();
	}

	@Operation(summary = "批量设置某商家的商品为选中或不选中", description = "批量设置某商家的商品为选中或不选中")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/store/{storeId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<Void> updateStoreAll(
			@NotNull(message = "卖家id不能为空") @PathVariable(name = "storeId") String storeId, boolean checked) {
		cartCommandService.checkedStore(storeId, checked);
		return Result.success();
	}

	@Operation(summary = "清空购物车", description = "清空购物车")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping("/clean")
	public Result<Void> clean() {
		cartCommandService.clean();
		return Result.success();
	}

	@Operation(summary = "删除购物车中的一个或多个产品", description = "删除购物车中的一个或多个产品")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/sku/remove")
	public Result<Void> delete(String[] skuIds) {
		cartCommandService.delete(skuIds);
		return Result.success();
	}

	@Operation(summary = "获取结算页面购物车详情", description = "获取结算页面购物车详情")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping("/checked")
	public Result<TradeDTO> cartChecked(@NotNull(message = "读取选中列表") String way) {
		return Result.success(this.cartQueryService.queryCheckedTradeDTO(CartTypeEnum.valueOf(way)));
	}

	@Operation(summary = "选择收货地址", description = "选择收货地址")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping("/shippingAddress")
	public Result<Void> shippingAddress(
			@NotNull(message = "收货地址ID不能为空") String shippingAddressId, String way) {
		cartCommandService.shippingAddress(shippingAddressId, way);
		return Result.success();
	}

	@Operation(summary = "选择配送方式", description = "选择配送方式")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping("/shippingMethod")
	public Result<Void> shippingMethod(
			@NotNull(message = "配送方式不能为空") String shippingMethod, String selleId, String way) {
		cartCommandService.shippingMethod(selleId, shippingMethod, way);
		return Result.success();
	}

	@Operation(summary = "选择发票", description = "选择发票")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping("/receipt")
	public Result<Void> selectReceipt(String way, ReceiptResult receiptResult) {
		cartCommandService.shippingReceipt(receiptResult, way);
		return Result.success();
	}

	@Operation(summary = "选择优惠券", description = "选择优惠券")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping("/coupon")
	public Result<Void> selectCoupon(
			String way,
			@NotNull(message = "优惠券id不能为空") String memberCouponId,
			boolean used) {
		cartCommandService.selectCoupon(memberCouponId, way, used);
		return Result.success();
	}

	@Operation(summary = "创建交易", description = "创建交易")
	@RequestLogger
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping(value = "/trade")
	public Result<TradeResult> crateTrade(@RequestBody TradeDTO tradeDTO) {
		return Result.success(this.cartCommandService.createTrade(tradeDTO));
	}
}
