package com.taotao.cloud.order.domain.order.repository;


import com.taotao.cloud.order.domain.order.aggregate.Order;
import com.taotao.cloud.order.domain.order.valueobject.User;

import java.util.Optional;

public interface OrderRepository {
	void save(Order it);

	Order byId(String id);

	Optional<Order> byIdOptional(String id);

	Order byIdAndCheckTenantShip(String id, User user);

	void delete(Order it);
}
