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
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Address
 *
 * @author shuigedeng
 * @version 2026.04
 * @since 2025-12-19 09:30:45
 */
public record Address(@Size(max = 20) String province,
					  @Size(max = 20) String city,
					  @Size(max = 20) String district,
					  @Size(max = 100) String address) {

	private static final String ADDRESS_JOINNER = "/";

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
