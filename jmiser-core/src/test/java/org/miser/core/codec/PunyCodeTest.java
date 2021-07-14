package org.miser.core.codec;

import org.junit.Assert;
import org.junit.Test;

public class PunyCodeTest {

	@Test
	public void encodeDecodeTest() {
		String text = "coder编码器";
		String strPunyCode = PunyCode.encode(text);
		Assert.assertEquals("coder-hz1i002rn3l", strPunyCode);
		String decode = PunyCode.decode("coder-hz1i002rn3l");
		Assert.assertEquals(text, decode);
		decode = PunyCode.decode("xn--coder-hz1i002rn3l");
		Assert.assertEquals(text, decode);
	}
}
