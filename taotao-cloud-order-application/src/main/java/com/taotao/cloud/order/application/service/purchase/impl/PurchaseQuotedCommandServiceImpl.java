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

package com.taotao.cloud.order.application.service.purchase.impl;

import com.taotao.cloud.order.application.service.purchase.PurchaseQuotedCommandService;
import com.taotao.cloud.order.application.service.purchase.PurchaseQuotedItemCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 采购单报价业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:55:44
 */
@Service
public class PurchaseQuotedCommandServiceImpl implements PurchaseQuotedCommandService {

    @Autowired private PurchaseQuotedItemCommandService purchaseQuotedItemService;

    //	@Override
    //	public List<PurchaseQuotedPO> getByPurchaseOrderId(String purchaseOrderId) {
    //		return List.of();
    //	}

    // @Override
    // @Transactional(rollbackFor = Exception.class)
    // public PurchaseQuotedVO addPurchaseQuoted(PurchaseQuotedVO purchaseQuotedVO) {
    //	PurchaseQuotedPO purchaseQuotedPO = new PurchaseQuotedPO();
    //	BeanUtils.copyProperties(purchaseQuotedVO, purchaseQuotedPO);
    //	// 添加报价单
    //	this.save(purchaseQuotedPO);
    //	// 添加采购单子内容
    //	purchaseQuotedItemService.addPurchaseQuotedItem(
    //		purchaseQuotedPO.getId(), purchaseQuotedVO.getPurchaseQuotedItems());
    //	return purchaseQuotedVO;
    // }
    //
    // @Override
    // public List<PurchaseQuotedPO> getByPurchaseOrderId(String purchaseOrderId) {
    //	LambdaQueryWrapper<PurchaseQuotedPO> lambdaQueryWrapper = Wrappers.lambdaQuery();
    //	lambdaQueryWrapper.eq(PurchaseQuotedPO::getPurchaseOrderId, purchaseOrderId);
    //	lambdaQueryWrapper.orderByDesc(PurchaseQuotedPO::getCreateTime);
    //	return this.list(lambdaQueryWrapper);
    // }
    //
    // @Override
    // public PurchaseQuotedVO getById(String id) {
    //	// 获取报价单
    //	PurchaseQuotedVO purchaseQuotedVO = new PurchaseQuotedVO();
    //	PurchaseQuotedPO purchaseQuotedPO = this.baseMapper.selectById(id);
    //	BeanUtils.copyProperties(purchaseQuotedPO, purchaseQuotedVO);
    //	// 获取报价单子内容
    //	purchaseQuotedVO.setPurchaseQuotedItems(
    //		purchaseQuotedItemService.purchaseQuotedItemList(id));
    //	return purchaseQuotedVO;
    // }
}
