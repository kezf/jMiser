package org.miser.crypto.test.symmetric;

import org.miser.core.util.RandomUtil;
import org.miser.crypto.SecureUtil;
import org.junit.Assert;
import org.junit.Test;

public class PBKDF2Test {

	@Test
	public void encryptTest(){
		final String s = SecureUtil.pbkdf2("123456".toCharArray(), RandomUtil.randomBytes(16));
		Assert.assertEquals(128, s.length());
	}
}
