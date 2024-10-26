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

package com.taotao.cloud.order.application.command.dict.executor;

import com.taotao.cloud.order.application.command.dict.dto.DictInsertCmd;
import com.taotao.cloud.order.domain.dict.service.DictDomainService;
import com.taotao.cloud.order.infrastructure.persistent.dict.mapper.DictMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 新增字典执行器.
 */
@Component
@RequiredArgsConstructor
public class DictInsertCmdExe {

    private final DictDomainService dictDomainService;
    // private final DictAdapter dictAdapter;
    // private final DictConvert dictConvert;
    private final DictMapper dictMapper;

    /**
     * 执行新增字典.
     *
     * @param cmd 新增字典参数
     * @return 执行新增结果
     */
    //	@DS(TENANT)
    public Boolean execute(DictInsertCmd cmd) {
        //		DictCO co = cmd.getDictCO();
        //		String type = co.getType();
        //		String value = co.getValue();
        //		Long count = dictMapper
        //			.selectCount(Wrappers.lambdaQuery(DictDO.class).eq(DictDO::getValue, value).eq(DictDO::getType, type));
        //		if (count > 0) {
        //			throw new BusinessException(String.format("类型为%s，值为%s的字典已存在，请重新填写", type, value));
        //		}
        //		return dictDomainService.insert(dictConvertor.toEntity(co));
        return null;
    }
}
