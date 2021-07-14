package org.miser.core.util;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

public class ModifierUtilTest {

	@Test
	public void hasModifierTest() throws NoSuchMethodException {
		Method method = ModifierUtilTest.class.getDeclaredMethod("ddd");
		Assert.assertTrue(ModifierUtil.hasModifier(method, ModifierUtil.ModifierType.PRIVATE));
		Assert.assertTrue(
				ModifierUtil.hasModifier(method, ModifierUtil.ModifierType.PRIVATE, ModifierUtil.ModifierType.STATIC));
	}

	@SuppressWarnings("unused")
	private static void ddd() {
	}
}
