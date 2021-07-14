package org.miser.core.lang;

import org.junit.Test;

public class AssertTest {
	
	@Test
	public void isNullTest(){
		String a = null;
		org.miser.core.lang.Assert.isNull(a);
	}
	@Test
	public void notNullTest(){
		String a = null;
		org.miser.core.lang.Assert.isNull(a);
	}

	@Test(expected = IllegalArgumentException.class)
	public void isTrueTest() {
		int i = 0;
		//noinspection ConstantConditions
		org.miser.core.lang.Assert.isTrue(i > 0, IllegalArgumentException::new);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void isTrueTest2() {
		int i = -1;
		//noinspection ConstantConditions
		org.miser.core.lang.Assert.isTrue(i >= 0, IndexOutOfBoundsException::new);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void isTrueTest3() {
		int i = -1;
		//noinspection ConstantConditions
		Assert.isTrue(i > 0, ()-> new IndexOutOfBoundsException("relation message to return"));
	}
}
