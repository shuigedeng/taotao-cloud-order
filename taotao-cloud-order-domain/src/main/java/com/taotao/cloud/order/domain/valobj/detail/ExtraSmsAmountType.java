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

package com.taotao.cloud.order.domain.valobj.detail;

public enum ExtraSmsAmountType {
    ONE_K(1000, 80),
    TWO_K(2000, 160),
    FIVE_K(5000, 350),
    TEN_K(10000, 600),
    TWENTY_K(20000, 1000),
    FIFTY_K(50000, 2500);

    private final int amount;
    private final int price;

    ExtraSmsAmountType(int amount, int price) {
        this.amount = amount;
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }
}
