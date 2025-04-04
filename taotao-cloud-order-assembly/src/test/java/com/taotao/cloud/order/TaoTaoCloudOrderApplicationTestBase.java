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

package com.taotao.cloud.order;

import com.taotao.boot.common.constant.CommonConstant;
import com.taotao.boot.common.utils.common.PropertyUtils;
import com.taotao.boot.test.TtcBootTestBase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class TaoTaoCloudOrderApplicationTestBase extends TtcBootTestBase {

    static {
        System.setProperty("spring.banner.location", "ttc-banner.txt");
        System.setProperty("spring.output.ansi.enabled", "ALWAYS");

        System.setProperty(CommonConstant.ACTIVE_PROFILES_PROPERTY, "dev");
        PropertyUtils.setDefaultProperty("taotao-cloud-order");
    }
}
