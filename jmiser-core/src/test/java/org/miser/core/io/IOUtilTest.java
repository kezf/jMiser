package org.miser.core.io;

import org.junit.Assert;
import org.junit.Test;
import org.miser.core.io.resource.ResourceUtil;

public class IOUtilTest {

	@Test
	public void readBytesTest() {
		final byte[] bytes = IOUtil.readBytes(ResourceUtil.getStream("logo.jpg"));
		Assert.assertEquals(22807, bytes.length);
	}
}
