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

package com.taotao.cloud.order.domain.valobj.plan;

public enum PlanType {
    FREE("免费版", 0, 1),
    BASIC("基础版", 680, 2),
    ADVANCED("高级版", 1380, 3),
    PROFESSIONAL("专业版", 6980, 4),
    FLAGSHIP("旗舰版", 12800, 5);

    private final String name;
    private final int price;
    private final int level;

    PlanType(String name, int price, int level) {
        this.name = name;
        this.price = price;
        this.level = level;
    }

    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
     * 获取
     *
     * @return 字符串
     * @since 2022.03
     */

    public String getName() {
        return name;
    }

    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */

    public int getPrice() {
        return price;
    }






    /**
     * covers 方法
     *
     * @param other other
     * @return 是否成功
     * @since 2022.03
     */

    public boolean covers(PlanType other) {
        return this.getPrice() >= other.getPrice();
    }

    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
    /**
     * 获取
     *
     * @return 结果数量
     * @since 2022.03
     */

    public int getLevel() {
        return level;
    }
}
