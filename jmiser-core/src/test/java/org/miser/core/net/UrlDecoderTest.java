package org.miser.core.net;

import org.miser.core.util.CharsetUtil;
import org.junit.Assert;
import org.junit.Test;

public class UrlDecoderTest {
	@Test
	public void decodeForPathTest(){
		Assert.assertEquals("+", URLDecoder.decodeForPath("+", CharsetUtil.CHARSET_UTF_8));
	}
}
