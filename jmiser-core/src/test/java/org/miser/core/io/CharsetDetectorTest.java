package org.miser.core.io;

import org.miser.core.io.resource.ResourceUtil;
import org.miser.core.util.CharsetUtil;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.Charset;

public class CharsetDetectorTest {

	@Test
	public void detectTest(){
		// 测试多个Charset对同一个流的处理是否有问题
		final Charset detect = CharsetDetector.detect(ResourceUtil.getStream("test.xml"),
				CharsetUtil.CHARSET_GBK, CharsetUtil.CHARSET_UTF_8);
		Assert.assertEquals(CharsetUtil.CHARSET_UTF_8, detect);
	}
}
