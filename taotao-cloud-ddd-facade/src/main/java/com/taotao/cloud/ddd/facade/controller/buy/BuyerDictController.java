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

package com.taotao.cloud.ddd.facade.controller.buy;

import com.taotao.boot.common.model.Result;
import com.taotao.boot.common.utils.log.LogUtils;
import com.taotao.cloud.ddd.application.command.dict.dto.DictGetQry;
import com.taotao.cloud.ddd.application.command.dict.dto.clientobject.DictCO;
import com.taotao.cloud.ddd.application.service.DeptsService;
import com.taotao.cloud.ddd.application.service.DictsService;
import com.taotao.cloud.ddd.domain.dept.service.DeptDomainService;
import com.taotao.cloud.ddd.domain.dict.service.DictDomainService;
import com.taotao.cloud.goods.api.feign.GoodsApi;
import com.taotao.boot.security.spring.annotation.NotAuth;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.aop.MeterTag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.sql.SQLIntegrityConstraintViolationException;
import lombok.AllArgsConstructor;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
/**
 * pc端-字典API
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-10-09 14:24:19
 */
@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/sys/buyer/dict")
@Tag(name = "pc端-字典API", description = "pc端-字典API")
public class BuyerDictController {

	private final DictsService dictsService;
	private final DeptsService deptsService;
	private final DictDomainService dictDomainService;
	private final DeptDomainService deptDomainService;
	private final GoodsApi goodsApi;

	//@MeterTag注解标准方法参数，key定义了显示时的名称，expression支持SpEL表达式，这里是获取当前参数name的值。
	@Timed("MeterDemoController.tag")
	@GetMapping("/tag")
	public Object tag(
		@MeterTag(key = "MeterDemoController#tag.name", expression = "#name") String name) {
		return "@MeterTag Annotation";
	}

	//该注解会统计方法调用的执行耗时情况，包括什么类，哪个方法等信息。 通过/prometheus Actuator接口查看
	//MeterDemoController_pp_seconds_count：调用次数。
	//MeterDemoController_pp_seconds_sum：总计耗时（总次数时间合计）。
	//MeterDemoController_pp_seconds_max：最大耗时时长。
	@Timed("BuyerDictController.pp")
	//该注解用来统计方法调用成功与失败情况
	@Counted("BuyerDictController.cc")
	@NotAuth
	@GetMapping("/add/{id}")
	@Operation(summary = "通过code查询所有字典列表")
	public Result<Boolean> add(@PathVariable(value = "id") Long id)
		throws SQLIntegrityConstraintViolationException {
//		Long result = goodsApi.countStoreGoodsNum(1L);

		DictGetQry dictGetQry = new DictGetQry();
		dictGetQry.setId(id);
		DictCO dictCO = dictsService.getById(dictGetQry);

		String traceId = TraceContext.traceId();
		LogUtils.info("调用-商品API---结果：{}", 111);

		return Result.success(false);
	}

	@NotAuth
	@GetMapping("/add1")
	@Operation(summary = "通过code查询所有字典列表")
	public Result<Boolean> add1() {
		return Result.success(true);
	}

	@GetMapping("/test/codexxxxx")
	@Operation(summary = "通过code查询所有字典列表")
	public Result<Boolean> testCode(@RequestParam String code) {
		// try {
		//	producerService.sendStringMsg();
		//	producerService.sendClassMsg();
		// } catch (PulsarClientException e) {
		//	LogUtils.error(e);
		// }

//        Dict byCode = service().findByCode(code);
//        LogUtils.info(String.valueOf(byCode));
		return Result.success(true);
	}

//    @NotAuth
//    @GetMapping("/testMybatisQueryStructure")
//    // @ApiOperation(value = "字典列表code查询", notes = "字典列表code查询")
//    public Result<Dict> testMybatisQueryStructure(@RequestParam Long dictId) {
//        DictQuery dictQuery = new DictQuery();
//        dictQuery.setDictId(dictId);
//        return Result.success(service().testMybatisQueryStructure(dictQuery));
//    }
}
