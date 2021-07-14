package org.miser.extra.expression;

import org.miser.core.lang.Dict;
import org.miser.extra.expression.engine.jexl.JexlEngine;
import org.miser.extra.expression.engine.jfireel.JfireELEngine;
import org.miser.extra.expression.engine.mvel.MvelEngine;
import org.miser.extra.expression.engine.rhino.RhinoEngine;
import org.miser.extra.expression.engine.spel.SpELEngine;
import org.junit.Assert;
import org.junit.Test;

public class ExpressionUtilTest {

	@Test
	public void evalTest(){
		final Dict dict = Dict.create()
				.set("a", 100.3)
				.set("b", 45)
				.set("c", -199.100);
		final Object eval = ExpressionUtil.eval("a-(b-c)", dict);
		Assert.assertEquals(-143.8, (double)eval, 2);
	}

	@Test
	public void jexlTest(){
		ExpressionEngine engine = new JexlEngine();

		final Dict dict = Dict.create()
				.set("a", 100.3)
				.set("b", 45)
				.set("c", -199.100);
		final Object eval = engine.eval("a-(b-c)", dict);
		Assert.assertEquals(-143.8, (double)eval, 2);
	}

	@Test
	public void mvelTest(){
		ExpressionEngine engine = new MvelEngine();

		final Dict dict = Dict.create()
				.set("a", 100.3)
				.set("b", 45)
				.set("c", -199.100);
		final Object eval = engine.eval("a-(b-c)", dict);
		Assert.assertEquals(-143.8, (double)eval, 2);
	}

	@Test
	public void jfireELTest(){
		ExpressionEngine engine = new JfireELEngine();

		final Dict dict = Dict.create()
				.set("a", 100.3)
				.set("b", 45)
				.set("c", -199.100);
		final Object eval = engine.eval("a-(b-c)", dict);
		Assert.assertEquals(-143.8, (double)eval, 2);
	}

	@Test
	public void spELTest(){
		ExpressionEngine engine = new SpELEngine();

		final Dict dict = Dict.create()
				.set("a", 100.3)
				.set("b", 45)
				.set("c", -199.100);
		final Object eval = engine.eval("#a-(#b-#c)", dict);
		Assert.assertEquals(-143.8, (double)eval, 2);
	}

	@Test
	public void rhinoTest(){
		ExpressionEngine engine = new RhinoEngine();

		final Dict dict = Dict.create()
				.set("a", 100.3)
				.set("b", 45)
				.set("c", -199.100);
		final Object eval = engine.eval("a-(b-c)", dict);
		Assert.assertEquals(-143.8, (double)eval, 2);
	}

}
