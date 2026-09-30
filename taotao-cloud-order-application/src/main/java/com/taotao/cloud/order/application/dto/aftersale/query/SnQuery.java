package com.taotao.cloud.order.application.dto.aftersale.query;

import com.taotao.boot.common.model.ddd.types.Query;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SnQuery implements Query {
	@NotBlank(message = "售后单号不能为空")
	private String sn;
}
