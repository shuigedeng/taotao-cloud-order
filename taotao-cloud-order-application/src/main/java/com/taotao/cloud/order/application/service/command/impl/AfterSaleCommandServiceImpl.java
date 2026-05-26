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

import com.taotao.cloud.order.application.dto.aftersale.command.AfterSaleAddCmd;
import com.taotao.cloud.order.application.service.command.AfterSaleCommandService;
import com.taotao.cloud.order.application.dto.aftersale.result.AfterSaleResult;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 售后业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:49:30
 */
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AfterSaleCommandServiceImpl implements AfterSaleCommandService {

	@Override
	public void saveAfterSale(AfterSaleAddCmd afterSaleAddCmd) {}

	@Override
	public void review(String afterSaleSn, String serviceStatus, String remark,
		BigDecimal actualRefundPrice) {}

	@Override
	public AfterSaleResult buyerDelivery(String afterSaleSn, String logisticsNo, Long logisticsId,
		LocalDateTime mDeliverTime) {
		return null;
	}

	@Override
	public void storeConfirm(String afterSaleSn, String serviceStatus, String remark) {}

	@Override
	public void refund(String afterSaleSn, String remark) {}

	@Override
	public AfterSaleResult complete(String afterSaleSn) {
		return null;
	}

	@Override
	public void cancel(String afterSaleSn) {}
}
