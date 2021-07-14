package org.miser.core.text;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.miser.core.date.DateUtil;
import org.miser.core.date.TimeInterval;
import org.miser.core.lang.Console;

/**
 * FastStringBuilder单元测试
 * 
 * @author Oliver
 *
 */
public class FastStringBuilderTest {

	/**
	 * FastStringBuilder的性能测试
	 */
	@Test
	@Ignore
	public void benchTest() {
		TimeInterval timer = DateUtil.timer();
		FastStringBuilder builder = FastStringBuilder.create();
		for (int i = 0; i < 1000000; i++) {
			builder.append("test");
			builder.reset();
		}
		Console.log(timer.interval());

		timer.restart();
		FastStringBuilder b2 = new FastStringBuilder();
		for (int i = 0; i < 1000000; i++) {
			b2.append("test");
			b2 = new FastStringBuilder();
		}
		Console.log(timer.interval());
	}

	@Test
	public void appendTest() {
		FastStringBuilder builder = FastStringBuilder.create();
		builder.append("aaa").append("你好").append('r');
		Assert.assertEquals("aaa你好r", builder.toString());
	}

	@Test
	public void insertTest() {
		FastStringBuilder builder = FastStringBuilder.create(1);
		builder.append("aaa").append("你好").append('r');
		builder.insert(3, "数据插入");
		Assert.assertEquals("aaa数据插入你好r", builder.toString());
	}

	@Test
	public void insertTest2() {
		FastStringBuilder builder = FastStringBuilder.create(1);
		builder.append("aaa").append("你好").append('r');
		builder.insert(8, "数据插入");
		Assert.assertEquals("aaa你好r  数据插入", builder.toString());
	}

	@Test
	public void resetTest() {
		FastStringBuilder builder = FastStringBuilder.create(1);
		builder.append("aaa").append("你好").append('r');
		builder.insert(3, "数据插入");
		builder.reset();
		Assert.assertEquals("", builder.toString());
	}

	@Test
	public void resetTest2() {
		FastStringBuilder builder = FastStringBuilder.create(1);
		builder.append("aaa").append("你好").append('r');
		builder.insert(3, "数据插入");
		builder.reset();
		builder.append("bbb".toCharArray());
		Assert.assertEquals("bbb", builder.toString());
	}

	@Test
	public void appendObjectTest() {
		FastStringBuilder builder = FastStringBuilder.create(1);
		builder.append(123).append(456.123D).append(true).append('\n');
		Assert.assertEquals("123456.123true\n", builder.toString());
	}

	@Test
	public void delTest() {
		// 删除全部测试
		FastStringBuilder fastBuilder = new FastStringBuilder("ABCDEFG");
		int length = fastBuilder.length();
		FastStringBuilder builder = fastBuilder.del(0, length);
		Assert.assertEquals("", builder.toString());
	}

	@Test
	public void delTest2() {
		// 删除中间部分测试
		FastStringBuilder fastBuilder = new FastStringBuilder("ABCDEFG");
		FastStringBuilder builder = fastBuilder.del(2, 6);
		Assert.assertEquals("ABG", builder.toString());
	}

	@Test
	public void delToTest() {
		FastStringBuilder fastBuilder = new FastStringBuilder("ABCDEFG");

		// 不处理
		FastStringBuilder builder = fastBuilder.delTo(7);
		Assert.assertEquals("ABCDEFG", builder.toString());

		// 删除全部
		builder = fastBuilder.delTo(0);
		Assert.assertEquals("", builder.toString());
	}
}
