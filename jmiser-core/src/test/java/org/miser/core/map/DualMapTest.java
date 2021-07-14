package org.miser.core.map;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

public class DualMapTest {

	@Test
	public void getTest() {
		DualMap<String, Integer> dualMap = new DualMap<>(new HashMap<>());
		dualMap.put("aaa", 111);
		dualMap.put("bbb", 222);

		Assert.assertEquals(new Integer(111), dualMap.get("aaa"));
		Assert.assertEquals(new Integer(222), dualMap.get("bbb"));

		Assert.assertEquals("aaa", dualMap.getKey(111));
		Assert.assertEquals("bbb", dualMap.getKey(222));
	}
}
