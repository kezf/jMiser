package org.miser.cache;

import org.junit.Assert;
import org.junit.Test;
import org.miser.cache.file.LFUFileCache;

/**
 * 文件缓存单元测试
 * 
 * @author Oliver
 *
 */
public class FileCacheTest {
	@Test
	public void lfuFileCacheTest() {
		LFUFileCache cache = new LFUFileCache(1000, 500, 2000);
		Assert.assertNotNull(cache);
	}
}
