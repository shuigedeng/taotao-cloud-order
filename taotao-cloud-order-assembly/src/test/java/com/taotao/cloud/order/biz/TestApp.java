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

package com.taotao.cloud.order.biz;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.taotao.boot.test.junitperf.core.annotation.TtcTest;
import com.taotao.boot.test.junitperf.core.report.impl.HtmlReporter;
import com.taotao.cloud.order.TaoTaoCloudOrderApplicationTestBase;
import com.taotao.cloud.order.application.service.DeptsService;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * TestApp
 *
 * @author shuigedeng
 * @version 2026.02
 * @since 2025-12-19 09:30:45
 */
public class TestApp extends TaoTaoCloudOrderApplicationTestBase {

    @Autowired
    private DeptsService deptsService;

    @TtcTest(duration = 1000, reporter = HtmlReporter.class)
    public void helloTest111() throws InterruptedException {
        int i = deptsService.hashCode();

        Thread.sleep(100);
        System.out.println("Hello Junit5");
    }

    @Test
    public void testMock() {
        // 创建Mock对象
        List<String> mockList = mock(List.class);

        // 设置Mock对象的行为
        when(mockList.get(0)).thenReturn("Mocked Value");

        // 调用Mock对象的方法
        String result = mockList.get(0);

        // 验证方法的调用次数和参数
        verify(mockList, times(1)).get(0);

        // 创建Stub对象
        List<String> stubList = new ArrayList<>();
        stubList.add("Stubbed Value");

        // 调用Stub对象的方法
        String result1 = stubList.get(0);

        System.out.println("asdfasf");
    }

    // @ValueSource：提供基本类型、字符串或枚举类型的参数值。
    //
    //
    // @CsvSource：通过逗号分隔的字符串提供多个参数值。
    //
    //
    // @CsvFileSource：从CSV文件中读取参数值。
    //
    //
    // @MethodSource：通过调用其他方法来提供参数值。
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void testSquare( int num ) {
        int result = num * num;
        Assertions.assertEquals(num * num, result);
    }
}
