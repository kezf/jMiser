package org.miser.extra.expression.engine;

import org.miser.core.lang.Singleton;
import org.miser.core.util.ServiceLoaderUtil;
import org.miser.core.util.StringUtil;
import org.miser.log.StaticLog;

import org.miser.extra.expression.ExpressionEngine;
import org.miser.extra.expression.ExpressionException;

/**
 * 表达式语言引擎工厂类，，用于根据用户引入的表达式jar，自动创建对应的引擎对象
 *
 * 
 * @author Oliver
 */
public class ExpressionFactory {

	/**
	 * 获得单例的{@link ExpressionEngine}
	 *
	 * @return 单例的{@link ExpressionEngine}
	 */
	public static ExpressionEngine get(){
		return Singleton.get(ExpressionEngine.class.getName(), ExpressionFactory::create);
	}

	/**
	 * 根据用户引入的表达式引擎jar，自动创建对应的拼音引擎对象<br>
	 * 推荐创建的引擎单例使用，此方法每次调用会返回新的引擎
	 *
	 * @return {@link ExpressionEngine}
	 */
	public static ExpressionEngine create() {
		final ExpressionEngine engine = doCreate();
		StaticLog.debug("Use [{}] Engine As Default.", StringUtil.removeSuffix(engine.getClass().getSimpleName(), "Engine"));
		return engine;
	}

	/**
	 * 根据用户引入的拼音引擎jar，自动创建对应的拼音引擎对象<br>
	 * 推荐创建的引擎单例使用，此方法每次调用会返回新的引擎
	 *
	 * @return {@link ExpressionEngine}
	 */
	private static ExpressionEngine doCreate() {
		final ExpressionEngine engine = ServiceLoaderUtil.loadFirstAvailable(ExpressionEngine.class);
		if(null != engine){
			return engine;
		}

		throw new ExpressionException("No expression jar found ! Please add one of it to your project !");
	}
}
