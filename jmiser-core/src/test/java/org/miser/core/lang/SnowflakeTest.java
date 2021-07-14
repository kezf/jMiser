package org.miser.core.lang;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;
import org.miser.core.util.IdUtil;
import org.miser.core.util.StringUtil;

/**
 * Snowflake单元测试
 * 
 * @author Oliver
 *
 */
public class SnowflakeTest {

	@Test
	public void snowflakeTest1() {
		// 构建Snowflake，提供终端ID和数据中心ID
		Snowflake idWorker = new Snowflake(0, 0);
		long nextId = idWorker.nextId();
		Assert.assertTrue(nextId > 0);
	}

	@Test
	public void snowflakeTest() {
		HashSet<Long> hashSet = new HashSet<>();

		// 构建Snowflake，提供终端ID和数据中心ID
		Snowflake idWorker = new Snowflake(0, 0);
		for (int i = 0; i < 1000; i++) {
			long id = idWorker.nextId();
			hashSet.add(id);
		}
		Assert.assertEquals(1000L, hashSet.size());
	}

	@Test
	public void snowflakeGetTest() {
		// 构建Snowflake，提供终端ID和数据中心ID
		Snowflake idWorker = new Snowflake(1, 2);
		long nextId = idWorker.nextId();

		Assert.assertEquals(1, idWorker.getWorkerId(nextId));
		Assert.assertEquals(2, idWorker.getDataCenterId(nextId));
		Assert.assertTrue(idWorker.getGenerateDateTime(nextId) - System.currentTimeMillis() < 10);
	}

	@Test
	public void getSnowflakeLengthTest() {
		for (int i = 0; i < 1000; i++) {
			final long l = IdUtil.getSnowflake(0, 0).nextId();
			Assert.assertEquals(19, StringUtil.toString(l).length());
		}
	}
}
