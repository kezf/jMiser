package org.miser.core.lang.func;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;

import org.miser.core.lang.SimpleCache;
import org.miser.core.util.ReflectUtil;

/**
 * Lambda相关工具类
 *
 * @author Oliver
 * 
 */
public class LambdaUtil {

	private static final SimpleCache<String, SerializedLambda> cache = new SimpleCache<>();

	/**
	 * 解析lambda表达式,加了缓存。 该缓存可能会在任意不定的时间被清除
	 *
	 * @param <T>  Lambda类型
	 * @param func 需要解析的 lambda 对象（无参方法）
	 * @return 返回解析后的结果
	 */
	public static <T> SerializedLambda resolve(Func1<T, ?> func) {
		return _resolve(func);
	}

	/**
	 * 获取lambda表达式函数（方法）名称
	 *
	 * @param <T>  Lambda类型
	 * @param func 函数（无参方法）
	 * @return 函数名称
	 */
	public static <T> String getMethodName(Func1<T, ?> func) {
		return resolve(func).getImplMethodName();
	}

	/**
	 * 解析lambda表达式,加了缓存。 该缓存可能会在任意不定的时间被清除
	 *
	 * @param func 需要解析的 lambda 对象
	 * @return 返回解析后的结果
	 */
	private static <T> SerializedLambda _resolve(Serializable func) {
		return cache.get(func.getClass().getName(), () -> ReflectUtil.invoke(func, "writeReplace"));
	}
}
