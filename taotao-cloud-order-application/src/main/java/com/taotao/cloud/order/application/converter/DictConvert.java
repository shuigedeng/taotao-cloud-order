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

package com.taotao.cloud.order.application.converter;

import com.taotao.cloud.order.application.dto.dict.dto.DictListQry;
import com.taotao.cloud.order.application.dto.dict.dto.clientobject.DictCO;
import com.taotao.cloud.order.domain.dict.entity.DictEntity;
import com.taotao.cloud.order.infrastructure.persistent.dict.po.DictPO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * DeptMapStruct
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 13:39:18
 */
@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DictConvert {

    /** 实例 */
    DictConvert INSTANCE = Mappers.getMapper(DictConvert.class);

    DictEntity convert(DictListQry dict);

    DictCO convert(DictEntity dictEntity);

    DictCO convert(DictPO dictEntity);
}
