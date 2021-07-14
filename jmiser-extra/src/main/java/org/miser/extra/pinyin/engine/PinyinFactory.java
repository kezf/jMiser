package org.miser.extra.pinyin.engine;

import org.miser.core.lang.Singleton;
import org.miser.core.util.ServiceLoaderUtil;
import org.miser.core.util.StringUtil;
import org.miser.log.StaticLog;

import org.miser.extra.pinyin.PinyinEngine;
import org.miser.extra.pinyin.PinyinException;

/**
 * 简单拼音引擎工厂，用于根据用户引入的拼音库jar，自动创建对应的拼音引擎对象
 *
 * @author Oliver
 */
public class PinyinFactory {

	/**
	 * 获得单例的PinyinEngine
	 *
	 * @return 单例的PinyinEngine
	 */
	public static PinyinEngine get(){
		return Singleton.get(PinyinEngine.class.getName(), PinyinFactory::create);
	}

	/**
	 * 根据用户引入的拼音引擎jar，自动创建对应的拼音引擎对象<br>
	 * 推荐创建的引擎单例使用，此方法每次调用会返回新的引擎
	 *
	 * @return {@link PinyinEngine}
	 */
	public static PinyinEngine create() {
		final PinyinEngine engine = doCreate();
		StaticLog.debug("Use [{}] Engine As Default.", StringUtil.removeSuffix(engine.getClass().getSimpleName(), "Engine"));
		return engine;
	}

	/**
	 * 根据用户引入的拼音引擎jar，自动创建对应的拼音引擎对象<br>
	 * 推荐创建的引擎单例使用，此方法每次调用会返回新的引擎
	 *
	 * @return {@link PinyinEngine}
	 */
	private static PinyinEngine doCreate() {
		final PinyinEngine engine = ServiceLoaderUtil.loadFirstAvailable(PinyinEngine.class);
		if(null != engine){
			return engine;
		}

		throw new PinyinException("No pinyin jar found ! Please add one of it to your project !");
	}
}
