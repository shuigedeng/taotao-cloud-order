package com.taotao.cloud.order.domain.order.valueobject;

public enum OrderStatus {
	CREATED("新建"),
	PAID("已支付"),
	REFUNDED("已退款");

	private final String name;

	OrderStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
