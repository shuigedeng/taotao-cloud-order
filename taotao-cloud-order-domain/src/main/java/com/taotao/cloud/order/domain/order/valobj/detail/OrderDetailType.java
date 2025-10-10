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

package com.taotao.cloud.order.domain.order.valobj.detail;

public enum OrderDetailType {
    PLAN("购买套餐"),
    EXTRA_MEMBER("增购成员"),
    EXTRA_STORAGE("增购存储空间"),
    EXTRA_SMS("增购短信量"),
    EXTRA_VIDEO_TRAFFIC("增购视频流量"),
    PLATE_PRINTING("码牌印刷");

    private final String name;

    OrderDetailType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
