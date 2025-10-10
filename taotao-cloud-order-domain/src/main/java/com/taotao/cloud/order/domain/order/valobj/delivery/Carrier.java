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

package com.taotao.cloud.order.domain.order.valobj.delivery;

public enum Carrier {
    EMS("EMS", "EMS"),
    SHUN_FENG("顺丰", "SFEXPRESS"),
    YUAN_TONG("圆通", "YTO"),
    ZHONG_TONG("中通", "ZTO"),
    ZHONG_TONG_56("中通快运", "ZTO56"),
    SHEN_TONG("申通", "STO"),
    YUN_DA("韵达", "YUNDA"),
    YUN_DA_56("韵达快运", "YUNDA56");

    private final String name;
    private final String type;

    Carrier(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
