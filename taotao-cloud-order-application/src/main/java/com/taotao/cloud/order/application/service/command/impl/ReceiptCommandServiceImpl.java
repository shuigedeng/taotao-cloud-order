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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.cloud.order.application.dto.own.order.command.OrderReceiptAddCommand;
import com.taotao.cloud.order.application.dto.own.order.query.ReceiptPageQuery;
import com.taotao.cloud.order.application.service.command.ReceiptCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 发票业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:55:14
 */
@RequiredArgsConstructor
@Service
public class ReceiptCommandServiceImpl implements ReceiptCommandService {
    @Override
    public IPage<OrderReceiptAddCommand> pageQuery(ReceiptPageQuery receiptPageQry) {
        return null;
    }

    //	@Override
    //	public ReceiptPO getByOrderSn(String orderSn) {
    //		return null;
    //	}
    //
    //	@Override
    //	public ReceiptPO getDetail(String id) {
    //		return null;
    //	}
    //
    //	@Override
    //	public Boolean saveReceipt(ReceiptPO receiptPO) {
    //		return null;
    //	}
    //
    //	@Override
    //	public ReceiptPO invoicing(Long receiptId) {
    //		return null;
    //	}

    // @Override
    // public IPage<OrderReceiptDTO> pageQuery(ReceiptPageQuery receiptPageQuery) {
    //	return this.baseMapper.getReceipt(receiptPageQuery.buildMpPage(),
    // receiptPageQuery.wrapper());
    // }
    //
    // @Override
    // public ReceiptPO getByOrderSn(String orderSn) {
    //	LambdaQueryWrapper<ReceiptPO> lambdaQueryWrapper = Wrappers.lambdaQuery();
    //	lambdaQueryWrapper.eq(ReceiptPO::getOrderSn, orderSn);
    //	return this.getOne(lambdaQueryWrapper);
    // }
    //
    // @Override
    // public ReceiptPO getDetail(String id) {
    //	return this.getById(id);
    // }
    //
    // @Override
    // public Boolean saveReceipt(ReceiptPO receiptPO) {
    //	LambdaQueryWrapper<ReceiptPO> queryWrapper = new LambdaQueryWrapper<>();
    //	queryWrapper.eq(ReceiptPO::getReceiptTitle, receiptPO.getReceiptTitle());
    //	queryWrapper.eq(ReceiptPO::getMemberId, receiptPO.getMemberId());
    //	if (receiptPO.getId() != null) {
    //		queryWrapper.ne(ReceiptPO::getId, receiptPO.getId());
    //	}
    //	if (this.getOne(queryWrapper) == null) {
    //		this.save(receiptPO);
    //	}
    //	return true;
    // }
    //
    // @Override
    // public ReceiptPO invoicing(Long receiptId) {
    //	// 根据id查询发票信息
    //	ReceiptPO receiptPO = this.getById(receiptId);
    //	if (receiptPO != null) {
    //		receiptPO.setReceiptStatus(1);
    //		this.saveOrUpdate(receiptPO);
    //		return receiptPO;
    //	}
    //	throw new BusinessException(ResultEnum.USER_RECEIPT_NOT_EXIST);
    // }
}
