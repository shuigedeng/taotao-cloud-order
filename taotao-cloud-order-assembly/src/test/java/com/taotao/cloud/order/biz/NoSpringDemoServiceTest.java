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

import com.taotao.boot.test.TtcBootTestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

// @ActiveProfiles("test")
// @TestPropertySource({"classpath:application-test.yml"})
// @SpringBootTest(classes = TaoTaoCloudDDDApplication.class, properties =
// "spring.main.allow-bean-definition-overriding=true")
/**
 * NoSpringDemoServiceTest
 *
 * @author shuigedeng
 * @version 2026.03
 * @since 2025-12-19 09:30:45
 */
@SpringBootTest
public class NoSpringDemoServiceTest extends TtcBootTestBase {

    @Test
    @DisplayName("Mock的正确姿势")
    void testMock() {
        //        // Mock 出一个目标对象的实例，注意不是 new 出来的真实对象
        //		IDemoService mockService = Mockito.mock(IDemoService.class);
        //
        //        // 假设我们仅对 serviceA 方法，并且入参等于 "asdf" 时进行打桩
        //        Mockito.when(mockService.serviceA(Mockito.eq("asdf")))
        //                .thenReturn("mock：serviceA方法入参等于asdf时的特定返回值");
        //
        //        // 由于上边已经打桩了，并且这个调用正好命中打桩规则，因次返回值将是 "mock：serviceA方法入参等于asdf时的特定返回值"
        //        String asdf = mockService.serviceA("asdf");
        //        System.out.println("asdf = " + asdf);
        //        Assertions.assertEquals("mock：serviceA方法入参等于asdf时的特定返回值", asdf);
        //
        //        // 虽然也是调用 serviceA 方法，但由于没有命中打桩规则，所以返回值是 null
        //        String qwer = mockService.serviceA("qwer");
        //        System.out.println("qwer = " + qwer);
        //        Assertions.assertNull(qwer);
        //
        //        // serviceB 根本没有打桩，但由于 mockService 这个对象实例是 Mock 出来的，
        //        // 所以 serviceB 的方法体代码不会被执行，并且返回值固定为 null，不管入参是什么
        //        for (int i = 0; i < 3; i++) {
        //            String returnValueFromRandom =
        // mockService.serviceB(RandomStringUtils.random(10));
        //            System.out.println("returnValueFromRandom 第[" + i + "]次 = " +
        // returnValueFromRandom);
        //            Assertions.assertNull(returnValueFromRandom);
        //        }
    }

    @Test
    @DisplayName("Spy的错误打桩姿势")
    void testBadSpy() {
        //        // Spy 一个目标对象的实例，注意不是 new 出来的 100% 真实的实例，也不是 100% 假的实例
        //        // 到底有多真，有多假，取决于打桩埋点的覆盖程度
        //		IDemoService spyService = Mockito.spy(IDemoService.class);
        //
        //        // 打桩错误示例：参照 Mock 打桩的写法，预期对 serviceA 方法打桩，
        //        // 并且当入参等于 "asdf" 时，返回特定值 "spy：serviceA方法入参等于asdf时的特定返回值"
        //        Mockito.when(spyService.serviceA(Mockito.eq("asdf")))
        //                .thenReturn("spy：serviceA方法入参等于asdf时的特定返回值");
        //
        //        // 由于上边已经打桩了，并且这个调用正好命中打桩规则，因次返回值将是 "spy：serviceA方法入参等于asdf时的特定返回值"
        //        String asdf = spyService.serviceA("asdf");
        //        System.out.println("asdf = " + asdf);
        //        Assertions.assertEquals("spy：serviceA方法入参等于asdf时的特定返回值", asdf);
        //        // 注意：上述对入参等于 "asdf" 时，被"spy错误打桩"的方法 serviceA 的返回值的断言，是可以跑通的
        //        // 但是，存在如下两个问题：
        //        // 1. serviceA 被真实调用了；（这一点往往不是我们的预期）
        //        // 2. serviceA 被真实调用时，它的实际入参，其实并不是 "asdf"，而是 null。
        //        // （想象一下如果 serviceA 方法体内真正运行时，极有可能由于实际入参是 null 而抛出异常中断执行，这是一种灾难）
        //
        //        // 下述调用，虽然 serviceA 被打桩，由于 "qwer" 并没有命中打桩点，所以它不会返回打桩点设定的返回值
        //        // 不同于 Mock 出来的对象，下述调用会真实地执行 serviceA 方法体代码块，而不是像 Mock 一样返回 null
        //        System.out.println("==============================================");
        //        String qwer = spyService.serviceA("qwer");
        //        System.out.println("qwer = " + qwer);
        //        Assertions.assertEquals("serviceA 真实返回：qwer", qwer);
        //
        //        // 同理，serviceB 方法没有被打桩，但由于 spyService 是 Spy 出来的对象实例，
        //        // 不同于 Mock 出来的对象实例返回 null，对 serviceB 的调用会真实的执行目标方法，并按真实情况返回
        //        System.out.println("==============================================");
        //        String zxcv = spyService.serviceB("zxcv");
        //        System.out.println("zxcv = " + zxcv);
        //        Assertions.assertEquals("serviceB 真实返回：zxcv", zxcv);
    }

    @Test
    @DisplayName("Spy的正确打桩姿势")
    void testGoodSpy() {
        //        // 同样，先用 Spy 方式创建一个目标对象的实例
        //		IDemoService spyService = Mockito.spy(IDemoService.class);
        //
        //        // 对 Spy 对象的正确打桩姿势，
        //        // （巧了，用这种 doReturn|doThrow|doAnswer(xxx).when(obj).methodXXX() 的方式，也适用于 Mock
        // 出来的对象）
        //        Mockito.doReturn("spy：serviceA方法入参等于asdf时的特定返回值2")
        //                // 注意这里 when 里面是对象实例的变量名，而不是一个 methodCall
        //                .when(spyService)
        //                // 再通过 when 泛型方法返回的Spy实例引用，对目标方法打桩
        //                .serviceA(Mockito.eq("asdf"));
        //
        //        // 由于上边已经打桩了，并且这个调用正好命中打桩规则，因次返回值将是 "spy：serviceA方法入参等于asdf时的特定返回值2"
        //        String asdf = spyService.serviceA("asdf");
        //        System.out.println("asdf = " + asdf);
        //        Assertions.assertEquals("spy：serviceA方法入参等于asdf时的特定返回值2", asdf);
        //        // 说明：由于上面正确的打桩姿势，上述被命中打桩规则的调用，不会触发目标方法的真实调用，不存在上述错误用法的2个问题。
        //
        //        // 对于调用打桩方法，但没有命中打桩入参条件的调用，表现行为与上述 testBadSpy 单测代码一样
        //        System.out.println("==============================================");
        //        String qwer = spyService.serviceA("qwer");
        //        System.out.println("qwer = " + qwer);
        //        Assertions.assertEquals("serviceA 真实返回：qwer", qwer);
        //
        //        // 同理，对于没有打桩的 spy 对象实例的其他方法，表现行为与上述 testBadSpy 单测代码一样
        //        System.out.println("==============================================");
        //        String zxcv = spyService.serviceB("zxcv");
        //        System.out.println("zxcv = " + zxcv);
        //        Assertions.assertEquals("serviceB 真实返回：zxcv", zxcv);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void testSquare( int num ) {
        int result = num * num;
        Assertions.assertEquals(num * num, result);
    }
}
