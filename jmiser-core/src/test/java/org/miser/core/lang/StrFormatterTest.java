package org.miser.core.lang;

import org.junit.Assert;
import org.junit.Test;
import org.miser.core.text.StringFormatter;

public class StrFormatterTest {

	@Test
	public void formatTest() {
		// 通常使用
		String result1 = StringFormatter.format("this is {} for {}", "a", "b");
		Assert.assertEquals("this is a for b", result1);

		// 转义{}
		String result2 = StringFormatter.format("this is \\{} for {}", "a", "b");
		Assert.assertEquals("this is {} for a", result2);

		// 转义\
		String result3 = StringFormatter.format("this is \\\\{} for {}", "a", "b");
		Assert.assertEquals("this is \\a for b", result3);
	}
}
