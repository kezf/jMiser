package org.miser.core.lang;

import org.junit.Assert;
import org.junit.Test;
import org.miser.core.text.SimHash;
import org.miser.core.util.StringUtil;

public class SimhashTest {

	@Test
	public void simTest() {
		String text1 = "我是 一个 普通 字符串";
		String text2 = "我是 一个 普通 字符串";

		SimHash simhash = new SimHash();
		long hash = simhash.hash(StringUtil.split(text1, ' '));
		Assert.assertTrue(hash != 0);

		simhash.store(hash);
		boolean duplicate = simhash.equals(StringUtil.split(text2, ' '));
		Assert.assertTrue(duplicate);
	}
}
