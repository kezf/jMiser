package org.miser.core.text;

import org.junit.Assert;
import org.junit.Test;

public class CharSequenceUtilTest {

	@Test
	public void addPrefixIfNotTest() {
		String str = "hello";
		String result = CharSequenceUtil.addPrefixIfNot(str, "he");
		Assert.assertEquals(str, result);

		result = CharSequenceUtil.addPrefixIfNot(str, "Good");
		Assert.assertEquals("Good" + str, result);
	}

	@Test
	public void addSuffixIfNotTest() {
		String str = "hello";
		String result = CharSequenceUtil.addSuffixIfNot(str, "llo");
		Assert.assertEquals(str, result);

		result = CharSequenceUtil.addSuffixIfNot(str, " is Good");
		Assert.assertEquals(str + " is Good", result);
	}

	// ------------------------------------------------------------------------
	// remove
}
