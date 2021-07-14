package org.miser.extra.expression.engine.jfireel;

import org.miser.extra.expression.ExpressionEngine;
import com.jfirer.jfireel.expression.Expression;

import java.util.Map;

/**
 * JfireEL引擎封装<br>
 * 见：https://gitee.com/eric_ds/jfireEL
 *
 * 
 * @author Oliver
 */
public class JfireELEngine implements ExpressionEngine {

	/**
	 * 构造
	 */
	public JfireELEngine(){
	}

	@Override
	public Object eval(String expression, Map<String, Object> context) {
		return Expression.parse(expression).calculate(context);
	}
}
