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

package com.taotao.cloud.ddd.api.dubbo;

import static com.taotao.boot.common.support.info.ApiVersionEnum.V2022_07;
import static com.taotao.boot.common.support.info.ApiVersionEnum.V2022_08;

import com.taotao.boot.common.support.info.Caller;
import com.taotao.boot.common.support.info.Create;
import com.taotao.boot.common.support.info.RpcInfo;
import com.taotao.boot.common.support.info.Update;
import com.taotao.cloud.ddd.api.dubbo.response.DictRpcResponse;

/**
 * ISysDictService
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2021-10-09 20:32:36
 */
public interface DictRpcService {

	/**
	 * 字典code查询
	 *
	 * @param code 代码
	 * @return {@link DictRpcResponse }
	 * @since 2022-06-29 21:45:44
	 */
	@RpcInfo(
		create = @Create(version = V2022_07, date = "2022-07-01 17:11:55"),
		update = {
			@Update(version = V2022_07, content = "主要修改了配置信息的接口查询", date = "2022-07-01 17:11:55"),
			@Update(version = V2022_08, content = "主要修改了配置信息的接口查询08", date = "2022-07-08 15:12:55")
		},
		caller = {
			@Caller(contacts = "张三", desc = "支付系统", sys = "支付系统", use = "调用字典查询获取详情"),
			@Caller(contacts = "李四", desc = "后台管理-字典管理-添加页面", sys = "后台管理", use = "查询字典")
		}
	)
	DictRpcResponse findByCode(Integer code);
}
