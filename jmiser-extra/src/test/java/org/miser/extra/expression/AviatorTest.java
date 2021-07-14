package org.miser.extra.expression;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.miser.core.date.DateUtil;
import org.miser.core.lang.Console;
import org.miser.core.lang.Dict;
import org.miser.extra.expression.engine.aviator.AviatorEngine;

import lombok.Data;

/**
 * Aviator引擎单元测试，来自https://jmiser.miser.org/pull/1203
 */
public class AviatorTest {

	@Test
	public void simpleTest(){
		Foo foo = new Foo(100, 3.14f, DateUtil.parseDate("2020-11-12"));
		ExpressionEngine engine = new AviatorEngine();
		String exp =
				"\"[foo i=\"+ foo.i + \", f=\" + foo.f + \", date.year=\" + (foo.date.year+1900) + \", date.month=\" + foo.date.month + \", bars[0].name=\" + #foo.bars[0].name + \"]\"";
		String result = (String) engine.eval(exp, Dict.create().set("foo", foo));
		Assert.assertEquals("[foo i=100, f=3.14, date.year=2020, date.month=10, bars[0].name=bar]", result);

		// Assignment.
		exp = "#foo.bars[0].name='hello aviator' ; #foo.bars[0].name";
		result = (String) engine.eval(exp, Dict.create().set("foo", foo));
		Assert.assertEquals("hello aviator", result);
		Assert.assertEquals("hello aviator", foo.bars[0].getName());

		exp = "foo.bars[0] = nil ; foo.bars[0]";
		result = (String) engine.eval(exp, Dict.create().set("foo", foo));
		Console.log("Execute expression: " + exp);
		Assert.assertNull(result);
		Assert.assertNull(foo.bars[0]);
	}

	@Data
	public static class Bar {
		public Bar() {
			this.name = "bar";
		}
		private String name;
	}

	@Data
	public static class Foo {
		int i;
		float f;
		Date date;
		Bar[] bars = new Bar[1];

		public Foo(final int i, final float f, final Date date) {
			this.i = i;
			this.f = f;
			this.date = date;
			this.bars[0] = new Bar();
		}
	}
}
