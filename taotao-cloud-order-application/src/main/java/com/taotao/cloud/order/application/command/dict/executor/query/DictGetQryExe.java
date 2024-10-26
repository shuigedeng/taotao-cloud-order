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

package com.taotao.cloud.order.application.command.dict.executor.query;

import com.taotao.cloud.order.application.command.dict.dto.DictGetQry;
import com.taotao.cloud.order.application.command.dict.dto.clientobject.DictCO;
import com.taotao.cloud.order.application.converter.DictConvert;
import com.taotao.cloud.order.domain.dict.service.DictDomainService;
import com.taotao.cloud.order.infrastructure.persistent.dict.mapper.DictMapper;
import com.taotao.cloud.order.infrastructure.persistent.dict.po.DictPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 查看字典执行器.
 */
@Component
@RequiredArgsConstructor
public class DictGetQryExe {

    private final DictDomainService dictDomainService;
    // private final DictAdapter dictAdapter;
    private final DictConvert dictConvert;
    private final DictMapper dictMapper;

    /**
     * 执行查看字典.
     * @param dictGetQry 查看字典参数
     * @return 字典
     */
    // @DS(TENANT)
    public DictCO execute(DictGetQry dictGetQry) {
        DictPO dictPO = dictMapper.selectById(dictGetQry.getId());
        return dictConvert.convert(dictPO);
    }
}
