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

package com.taotao.cloud.order.application.service.aftersale.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.cloud.order.application.dto.aftersale.query.AfterSaleReasonPageQry;
import com.taotao.cloud.order.application.service.aftersale.AfterSaleReasonCommandService;
import com.taotao.cloud.order.infrastructure.persistent.mapper.aftersale.AfterSaleReasonMapper;
import com.taotao.cloud.order.infrastructure.persistent.persistence.aftersale.AfterSaleReasonPO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 售后原因业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:49:27
 */
@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AfterSaleReasonCommandServiceImpl extends ServiceImpl<AfterSaleReasonMapper, AfterSaleReasonPO>
	implements AfterSaleReasonCommandService {
	@Override
	public List<AfterSaleReasonPO> afterSaleReasonList(String serviceType) {
		return List.of();
	}

	@Override
	public Boolean editAfterSaleReason(AfterSaleReasonPO afterSaleReasonPO) {
		return null;
	}

	@Override
	public IPage<AfterSaleReasonPO> pageQuery(AfterSaleReasonPageQry afterSaleReasonPageQry) {
		return null;
	}

	//@Override
	//public List<AfterSaleReasonPO> afterSaleReasonList(String serviceType) {
	//	LambdaQueryWrapper<AfterSaleReasonPO> lambdaQueryWrapper = Wrappers.lambdaQuery();
	//	lambdaQueryWrapper.eq(AfterSaleReasonPO::getServiceType, serviceType);
	//	return this.list(lambdaQueryWrapper);
	//}
	//
	//@Override
	//public Boolean editAfterSaleReason(AfterSaleReasonPO afterSaleReasonPO) {
	//	LambdaUpdateWrapper<AfterSaleReasonPO> lambdaQueryWrapper = Wrappers.lambdaUpdate();
	//	lambdaQueryWrapper.eq(AfterSaleReasonPO::getId, afterSaleReasonPO.getId());
	//	lambdaQueryWrapper.set(AfterSaleReasonPO::getReason, afterSaleReasonPO.getReason());
	//	lambdaQueryWrapper.set(AfterSaleReasonPO::getServiceType, afterSaleReasonPO.getServiceType());
	//	this.update(lambdaQueryWrapper);
	//	return true;
	//}
	//
	//@Override
	//public IPage<AfterSaleReasonPO> pageQuery(AfterSaleReasonPageQuery afterSaleReasonPageQuery) {
	//	LambdaQueryWrapper<AfterSaleReasonPO> queryWrapper = Wrappers.lambdaQuery();
	//	queryWrapper.eq(AfterSaleReasonPO::getServiceType, afterSaleReasonPageQuery.getServiceType());
	//	return this.page(afterSaleReasonPageQuery.buildMpPage(), queryWrapper);
	//}
}
