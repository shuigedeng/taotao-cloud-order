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

package com.taotao.cloud.order.application.service.command;

import com.taotao.boot.ddd.model.application.service.CommandService;
import com.taotao.cloud.order.application.dto.cart.TradeDTO;
import com.taotao.cloud.order.application.dto.order.result.ReceiptResult;
import com.taotao.cloud.order.common.enums.cart.CartTypeEnum;
import com.taotao.cloud.order.application.dto.trade.result.TradeResult;

/**
 * 购物车业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:49:41
 */
public interface CartCommandService extends CommandService {

    void add(String skuId, Integer num, String cartType, Boolean cover);

    void checked(String skuId, boolean checked);

    void checkedStore(String storeId, boolean checked);

    void checkedAll(boolean checked);

    void delete(String[] skuIds);

    void clean();

    void cleanChecked(CartTypeEnum way);

    void resetTradeDTO(TradeDTO tradeDTO);

    void shippingAddress(String shippingAddressId, String way);

    void shippingReceipt(ReceiptResult receiptResult, String way);

    void shippingMethod(String storeId, String deliveryMethod, String way);

    void selectCoupon(String couponId, String way, boolean use);

    TradeResult createTrade(TradeDTO tradeDTO);
}
