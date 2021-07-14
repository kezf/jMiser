package org.miser.core.net;

import org.miser.core.util.CharsetUtil;
import org.junit.Assert;
import org.junit.Test;

public class URLEncoderTest {

	@Test
	public void encodeTest(){
		String encode = URLEncoder.DEFAULT.encode("+", CharsetUtil.CHARSET_UTF_8);
		Assert.assertEquals("+", encode);

		encode = URLEncoder.DEFAULT.encode(" ", CharsetUtil.CHARSET_UTF_8);
		Assert.assertEquals("%20", encode);
	}
}
