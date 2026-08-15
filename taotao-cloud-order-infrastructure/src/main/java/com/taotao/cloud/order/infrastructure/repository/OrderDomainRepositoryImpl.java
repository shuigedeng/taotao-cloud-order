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

package com.taotao.cloud.order.infrastructure.repository;

import com.taotao.cloud.order.domain.aggregate.OrderAgg;
import com.taotao.cloud.order.domain.repository.OrderDomainRepository;
import com.taotao.cloud.order.domain.valobj.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * OrderDomainRepositoryImpl
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
@Service
@AllArgsConstructor
public class OrderDomainRepositoryImpl implements OrderDomainRepository {

	@Override
	public void save( OrderAgg it ) {

	}

	@Override
	public OrderAgg byId( String id ) {
		return null;
	}

	@Override
	public Optional<OrderAgg> byIdOptional( String id ) {
		return Optional.empty();
	}

	@Override
	public OrderAgg byIdAndCheckTenantShip( String id, User user ) {
		return null;
	}

	@Override
	public void delete( OrderAgg it ) {

	}
}
