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

package com.taotao.cloud.order.infrastructure.persistent.repository.cls;

import com.taotao.cloud.order.infrastructure.persistent.persistence.order.OrderInfoPO;
import com.taotao.boot.webagg.repository.BaseClassSuperRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

/**
 * @author shuigedeng
 * @version 2022.03
 * @since 2020/10/22 12:46
 */
@Repository
public class OrderInfoRepository extends BaseClassSuperRepository<OrderInfoPO, Long> {

    public OrderInfoRepository(EntityManager em) {
        super(OrderInfoPO.class, em);
    }
	//
    //public static final QOrderInfo ORDER_INFO = QOrderInfo.orderInfo;
	//
    //public OrderInfoPO findOrderInfoById(Long id) {
    //    OrderInfoPO fetch = jpaQueryFactory()
    //            .selectFrom(ORDER_INFO)
    //            .where(ORDER_INFO.id.eq(id))
    //            .fetchOne();
	//
    //    OrderInfoPO t = getById(id);
	//
    //    return fetch;
    //}
}
