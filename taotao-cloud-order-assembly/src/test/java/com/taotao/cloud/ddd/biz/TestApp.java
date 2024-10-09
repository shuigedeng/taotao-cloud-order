package com.taotao.cloud.order.biz;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.taotao.boot.data.datasource.dynamic.spring.TestService;
import com.taotao.boot.test.junitperf.core.annotation.TtcTest;
import com.taotao.boot.test.junitperf.core.report.impl.HtmlReporter;
import com.taotao.cloud.order.TaoTaoCloudDDDApplicationTestBase;
import com.taotao.cloud.order.application.service.DeptsService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

public class TestApp extends TaoTaoCloudDDDApplicationTestBase {

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

	//@ParameterizedTest 注解用于标记一个参数化测试方法。与普通的 @Test 注解不同，
	// @ParameterizedTest 注解的方法将接受一个或多个参数，并根据提供的参数执行多次测试。以下是一个示例：

	//@ValueSource：提供基本类型、字符串或枚举类型的参数值。
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
	public void testSquare(int num) {
		int result = num * num;
		Assertions.assertEquals(num * num, result);
	}


}
