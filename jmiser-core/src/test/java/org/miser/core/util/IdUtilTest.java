package org.miser.core.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * {@link IdUtil} 单元测试
 *
 * @author Oliver
 *
 */
public class IdUtilTest {

	@Test
	public void randomUUIDTest() {
		String simpleUUID = IdUtil.simpleUUID();
		Assert.assertEquals(32, simpleUUID.length());

		String randomUUID = IdUtil.randomUUID();
		Assert.assertEquals(36, randomUUID.length());
	}

	@Test
	public void fastUUIDTest() {
		String simpleUUID = IdUtil.fastSimpleUUID();
		Assert.assertEquals(32, simpleUUID.length());

		String randomUUID = IdUtil.fastUUID();
		Assert.assertEquals(36, randomUUID.length());
	}

	@Test
	public void objectIdTest() {
		String id = IdUtil.objectId();
		Assert.assertEquals(24, id.length());
	}

	@Test
	public void getDataCenterIdTest() {
		final long dataCenterId = IdUtil.getDataCenterId(Long.MAX_VALUE);
		Assert.assertTrue(dataCenterId >= 1);
	}
}
