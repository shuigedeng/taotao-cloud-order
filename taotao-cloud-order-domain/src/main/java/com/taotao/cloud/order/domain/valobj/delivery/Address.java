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

package com.taotao.cloud.order.domain.valobj.delivery;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Address
 *
 * @author shuigedeng
 * @version 2026.03
 * @since 2025-12-19 09:30:45
 */
@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class Address {

    private static final String ADDRESS_JOINNER = "/";

    @Size(max = 20)
    private final String province;

    @Size(max = 20)
    private final String city;

    @Size(max = 20)
    private final String district;

    @Size(max = 100)
    private final String address;

    public static String joinAddress( String... addressPart ) {
        return String.join(ADDRESS_JOINNER, addressPart);
    }

    public boolean isFilled() {
        return isNotBlank(province)
                || isNotBlank(city)
                || isNotBlank(district)
                || isNotBlank(address);
    }

    public Set<String> indexedValues() {
        if (isBlank(province)) {
            return null;
        }

        Set<String> results = new HashSet<>();
        results.add(province);
        if (isNotBlank(city)) {
            results.add(joinAddress(province, city));
        }

        if (isNotBlank(city) && isNotBlank(district)) {
            results.add(joinAddress(province, city, district));
        }
        return Set.copyOf(results);
    }

    public String toText() {
        return Stream.of(province, city, district, address)
                .filter(StringUtils::isNotBlank)
                .collect(joining());
    }
}
