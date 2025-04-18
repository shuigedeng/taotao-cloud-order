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

package com.taotao.cloud.order.application.service.trade.impl;

import com.taotao.cloud.order.application.service.trade.OrderLogService;
import org.springframework.stereotype.Service;

/**
 * 订单日志业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:55:53
 */
@Service
public class OrderLogServiceImpl  implements
    OrderLogService {

//    @Override
//    public List<OrderLogPO> getOrderLog(String orderSn) {
//        LambdaQueryWrapper<OrderLogPO> lambdaQueryWrapper = Wrappers.lambdaQuery();
//        lambdaQueryWrapper.eq(OrderLogPO::orderSn, orderSn);
//        return this.list(lambdaQueryWrapper);
//    }
//
//	@Override
//	public IPage<OrderLogPO> pageQuery(OrderLogPageQry orderLogPageQry) {
//		return null;
//	}

	//@Override
    //public IPage<OrderLogPO> pageQuery(OrderLogPageQry orderLogPageQry) {
    //    LambdaQueryWrapper<OrderLogPO> lambdaQueryWrapper = Wrappers.lambdaQuery();
    //    // todo 需要设置条件
	//
    //    return this.page(orderLogPageQry.buildMpPage(), lambdaQueryWrapper);
    //}
}
