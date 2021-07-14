package org.miser.core.lang;

import org.miser.core.collection.ConcurrentHashSet;
import org.miser.core.thread.ThreadUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class UUIDTest {

	/**
	 * 测试UUID是否存在重复问题
	 */
	@Test
	public void fastUUIDTest(){
		Set<String> set = new ConcurrentHashSet<>(100);
		ThreadUtil.concurrencyTest(100, ()-> set.add(UUID.fastUUID().toString()));
		Assert.assertEquals(100, set.size());
	}
}
