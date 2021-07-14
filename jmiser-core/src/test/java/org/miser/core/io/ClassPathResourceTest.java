package org.miser.core.io;

import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.miser.core.io.resource.ClassPathResource;
import org.miser.core.util.StringUtil;

/**
 * ClassPath资源读取测试
 *
 * @author Oliver
 */
public class ClassPathResourceTest {

	@Test
	public void readStringTest() {
		ClassPathResource resource = new ClassPathResource("test.properties");
		String content = resource.readUtf8Str();
		Assert.assertTrue(StringUtil.isNotEmpty(content));
	}

	@Test
	public void readStringTest2() {
		// 读取classpath根目录测试
		ClassPathResource resource = new ClassPathResource("/");
		String content = resource.readUtf8Str();
		Assert.assertTrue(StringUtil.isNotEmpty(content));
	}

	@Test
	public void readTest() throws IOException {
		ClassPathResource resource = new ClassPathResource("test.properties");
		Properties properties = new Properties();
		properties.load(resource.getStream());

		Assert.assertEquals("1", properties.get("a"));
		Assert.assertEquals("2", properties.get("b"));
	}

	@Test
	public void readFromJarTest() {
		// 测试读取junit的jar包下的LICENSE-junit.txt文件
		final ClassPathResource resource = new ClassPathResource("LICENSE-junit.txt");

		String result = resource.readUtf8Str();
		Assert.assertNotNull(result);

		// 二次读取测试，用于测试关闭流对再次读取的影响
		result = resource.readUtf8Str();
		Assert.assertNotNull(result);
	}

	@Test
	public void getAbsTest() {
		final ClassPathResource resource = new ClassPathResource("LICENSE-junit.txt");
		String absPath = resource.getAbsolutePath();
		Assert.assertTrue(absPath.contains("LICENSE-junit.txt"));
	}
}
