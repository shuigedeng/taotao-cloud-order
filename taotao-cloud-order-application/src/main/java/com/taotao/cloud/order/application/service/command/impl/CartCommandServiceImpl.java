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

package com.taotao.cloud.order.application.service.command.impl;

import com.taotao.cloud.order.application.dto.cart.TradeDTO;
import com.taotao.cloud.order.application.dto.order.result.ReceiptResult;
import com.taotao.cloud.order.application.service.command.CartCommandService;
import com.taotao.cloud.order.common.enums.cart.CartTypeEnum;
import com.taotao.cloud.order.application.dto.trade.result.TradeResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 购物车业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:49:47
 */
@Service
@RequiredArgsConstructor
public class CartCommandServiceImpl implements CartCommandService {

	@Override
	public void add(String skuId, Integer num, String cartType, Boolean cover) {}

	@Override
	public void checked(String skuId, boolean checked) {}

	@Override
	public void checkedStore(String storeId, boolean checked) {}

	@Override
	public void checkedAll(boolean checked) {}

	@Override
	public void delete(String[] skuIds) {}

	@Override
	public void clean() {}

	@Override
	public void cleanChecked(CartTypeEnum way) {}

	@Override
	public void resetTradeDTO(TradeDTO tradeDTO) {}

	@Override
	public void shippingAddress(String shippingAddressId, String way) {}

	@Override
	public void shippingReceipt(ReceiptResult receiptResult, String way) {}

	@Override
	public void shippingMethod(String storeId, String deliveryMethod, String way) {}

	@Override
	public void selectCoupon(String couponId, String way, boolean use) {}

	@Override
	public TradeResult createTrade(TradeDTO tradeDTO) {
		return null;
	}
}
