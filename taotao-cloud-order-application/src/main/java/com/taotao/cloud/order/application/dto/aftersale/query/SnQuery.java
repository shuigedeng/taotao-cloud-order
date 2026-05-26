package com.taotao.cloud.order.application.dto.aftersale.query;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SnQuery {
	@NotBlank(message = "售后单号不能为空")
	private String sn;
}
