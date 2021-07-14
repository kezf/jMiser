package org.miser.extra.expression.engine.jexl;

import org.miser.extra.expression.ExpressionEngine;
import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.MapContext;

import java.util.Map;

/**
 * Jexl3引擎封装<br>
 * 见：https://github.com/apache/commons-jexl
 *
 * 
 * @author Oliver
 */
public class JexlEngine implements ExpressionEngine {

	private final org.apache.commons.jexl3.JexlEngine engine;

	public JexlEngine(){
		engine = (new JexlBuilder()).cache(512).strict(true).silent(false).create();
	}

	@Override
	public Object eval(String expression, Map<String, Object> context) {
		return engine.createExpression(expression).evaluate(new MapContext(context));
	}

	/**
	 * 获取{@link org.apache.commons.jexl3.JexlEngine}
	 *
	 * @return {@link org.apache.commons.jexl3.JexlEngine}
	 */
	public org.apache.commons.jexl3.JexlEngine getEngine() {
		return this.engine;
	}
}
