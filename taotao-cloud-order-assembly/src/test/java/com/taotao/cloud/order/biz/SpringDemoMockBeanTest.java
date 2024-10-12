package com.taotao.cloud.order.biz;

import com.taotao.cloud.order.TaoTaoCloudOrderApplication;
import com.taotao.cloud.order.application.service.DeptsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

//@ActiveProfiles("test")
//@TestPropertySource({"classpath:application-test.yml"})
@SpringBootTest(classes = TaoTaoCloudOrderApplication.class)
public class SpringDemoMockBeanTest {
    @MockBean
	DeptsService mockBeanService;

    @Test
    @DisplayName("MockBean的正确姿势")
    void testMock() {
        // 不同于普通 Mock 方式，这里不需要调用 Mockito.mock(xxx.class) 创建 mock 对象实例
        // 我们已经通过 @MockBean 的方式，将一个 mock 对象的实例放入了 Spring IoC ApplicationContext 中
        // 注意到 Spring IoC 默认是单例的，也就是当前 ApplicationContext 中，只有一个 mock 出来的 DemoBeanService 实例
        // 所以，如果它打桩不全的话，在当前这个 IoC 中，调用没有被打桩的方法，将一律返回 null
        // 因此在不同的 xxxTest.java 中出现不同 MockBean 时，会触发 Spring 上下文重建，写的 MockBean 越多，整个工程单测就越慢

//        // 假设我们仅对 serviceA 方法，并且入参等于 "asdf" 时进行打桩
//        Mockito.when(mockBeanService.serviceA(Mockito.eq("asdf")))
//                .thenReturn("MockBean：serviceA方法入参等于asdf时的特定返回值");
//
//        // 由于上边已经打桩了，并且这个调用正好命中打桩规则，因次返回值将是 "MockBean：serviceA方法入参等于asdf时的特定返回值"
//        String asdf = mockBeanService.serviceA("asdf");
//        System.out.println("asdf = " + asdf);
//        Assertions.assertEquals("MockBean：serviceA方法入参等于asdf时的特定返回值", asdf);
//
//        // 虽然也是调用 serviceA 方法，但由于没有命中打桩规则，所以返回值是 null
//        String qwer = mockBeanService.serviceA("qwer");
//        System.out.println("qwer = " + qwer);
//        Assertions.assertNull(qwer);
//
//        // serviceB 根本没有打桩，但由于 mockBeanService 这个对象实例是 Mock 出来的，
//        // 所以 serviceB 的方法体代码不会被执行，并且返回值固定为 null，不管入参是什么
//        for (int i = 0; i < 3; i++) {
//            String returnValueFromRandom = mockBeanService.serviceB(RandomStringUtils.random(10));
//            System.out.println("returnValueFromRandom 第[" + i + "]次 = " + returnValueFromRandom);
//            Assertions.assertNull(returnValueFromRandom);
//        }
    }
}
