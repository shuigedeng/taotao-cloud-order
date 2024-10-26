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

import com.taotao.cloud.order.application.command.dict.dto.DictDeleteCmd;
import com.taotao.cloud.order.domain.dict.service.DictDomainService;
import com.taotao.cloud.order.infrastructure.persistent.dict.mapper.DictMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 删除字典执行器.
 *
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DictDeleteCmdExe {

    private final DictDomainService dictDomainService;
    // private final DictAdapter dictAdapter;
    // private final DictConvert dictConvert;
    private final DictMapper dictMapper;

    /**
     * 执行删除字典.
     * @param cmd 删除字典参数
     * @return 执行删除结果
     */
    //	@DS(TENANT)
    public Boolean execute(DictDeleteCmd cmd) {
        return dictDomainService.deleteById(cmd.getId());
    }
}
