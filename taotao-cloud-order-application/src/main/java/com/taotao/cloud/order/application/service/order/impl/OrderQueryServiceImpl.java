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

package com.taotao.cloud.order.application.service.order.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.cloud.order.application.dto.cart.clientobject.OrderExportCO;
import com.taotao.cloud.order.application.dto.order.clientobject.OrderDetailCO;
import com.taotao.cloud.order.application.dto.order.clientobject.OrderSimpleCO;
import com.taotao.cloud.order.application.dto.order.clientobject.PaymentLogCO;
import com.taotao.cloud.order.application.dto.order.query.OrderPageQry;
import com.taotao.cloud.order.application.service.order.OrderQueryService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import zipkin2.storage.Traces;

import java.math.BigDecimal;
import java.util.List;

/**
 * 子订单业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-28 08:54:47
 */
@AllArgsConstructor
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderQueryServiceImpl implements OrderQueryService {


}
