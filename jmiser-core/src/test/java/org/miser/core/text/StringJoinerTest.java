package org.miser.core.text;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.miser.core.collection.CollUtil;
import org.miser.core.collection.ListUtil;

public class StringJoinerTest {

	@Test
	public void joinIntArrayTest() {
		int[] a = { 1, 2, 3, 4, 5 };
		final StringJoiner append = StringJoiner.of(",").append(a);
		Assert.assertEquals("1,2,3,4,5", append.toString());
	}

	@Test
	public void joinEmptyTest() {
		List<String> list = new ArrayList<>();
		final StringJoiner append = StringJoiner.of(",").append(list);
		Assert.assertEquals("", append.toString());
	}

	@Test
	public void noJoinTest() {
		final StringJoiner append = StringJoiner.of(",");
		Assert.assertEquals("", append.toString());
	}

	@Test
	public void joinMultiArrayTest() {
		final StringJoiner append = StringJoiner.of(",");
		append.append(new Object[] { ListUtil.of("1", "2"), CollUtil.newHashSet("3", "4") });
		Assert.assertEquals("1,2,3,4", append.toString());
	}

	@Test
	public void joinNullModeTest() {
		StringJoiner append = StringJoiner.of(",").setNullMode(StringJoiner.NullMode.IGNORE).append("1")
				.append((Object) null).append("3");
		Assert.assertEquals("1,3", append.toString());

		append = StringJoiner.of(",").setNullMode(StringJoiner.NullMode.TO_EMPTY).append("1").append((Object) null)
				.append("3");
		Assert.assertEquals("1,,3", append.toString());

		append = StringJoiner.of(",").setNullMode(StringJoiner.NullMode.NULL_STRING).append("1").append((Object) null)
				.append("3");
		Assert.assertEquals("1,null,3", append.toString());
	}

	@Test
	public void joinWrapTest() {
		StringJoiner append = StringJoiner.of(",", "[", "]").append("1").append("2").append("3");
		Assert.assertEquals("[1,2,3]", append.toString());

		append = StringJoiner.of(",", "[", "]").setWrapElement(true).append("1").append("2").append("3");
		Assert.assertEquals("[1],[2],[3]", append.toString());
	}
}
