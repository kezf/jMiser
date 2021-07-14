package org.miser.extra.tokenizer.engine;

import org.miser.core.lang.Singleton;
import org.miser.core.util.ServiceLoaderUtil;
import org.miser.core.util.StringUtil;
import org.miser.extra.tokenizer.TokenizerEngine;
import org.miser.extra.tokenizer.TokenizerException;
import org.miser.log.StaticLog;

/**
 * 简单分词引擎工厂，用于根据用户引入的分词引擎jar，自动创建对应的引擎
 * 
 * @author Oliver
 *
 */
public class TokenizerFactory {

	/**
	 * 根据用户引入的模板引擎jar，自动创建对应的分词引擎对象<br>
	 * 获得的是单例的TokenizerEngine
	 *
	 * @return 单例的TokenizerEngine
	 * 
	 */
	public static TokenizerEngine get(){
		return Singleton.get(TokenizerEngine.class.getName(), TokenizerFactory::create);
	}

	/**
	 * 根据用户引入的分词引擎jar，自动创建对应的分词引擎对象
	 * 
	 * @return {@link TokenizerEngine}
	 */
	public static TokenizerEngine create() {
		final TokenizerEngine engine = doCreate();
		StaticLog.debug("Use [{}] Tokenizer Engine As Default.", StringUtil.removeSuffix(engine.getClass().getSimpleName(), "Engine"));
		return engine;
	}

	/**
	 * 根据用户引入的分词引擎jar，自动创建对应的分词引擎对象
	 * 
	 * @return {@link TokenizerEngine}
	 */
	private static TokenizerEngine doCreate() {
		final TokenizerEngine engine = ServiceLoaderUtil.loadFirstAvailable(TokenizerEngine.class);
		if(null != engine){
			return engine;
		}

		throw new TokenizerException("No tokenizer found ! Please add some tokenizer jar to your project !");
	}
}
