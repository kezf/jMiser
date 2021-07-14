package org.miser.core.stream;

import java.util.Collections;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collector;

import org.miser.core.util.StringUtil;

/**
 * 可变的汇聚操作{@link Collector} 相关工具封装
 *
 * @author Oliver
 * 
 */
public class CollectorUtil {

	/**
	 * 提供任意对象的Join操作的{@link Collector}实现，对象默认调用toString方法
	 *
	 * @param delimiter 分隔符
	 * @param <T>       对象类型
	 * @return {@link Collector}
	 */
	public static <T> Collector<T, ?, String> joining(CharSequence delimiter) {
		return joining(delimiter, Object::toString);
	}

	/**
	 * 提供任意对象的Join操作的{@link Collector}实现
	 *
	 * @param delimiter    分隔符
	 * @param toStringFunc 自定义指定对象转换为字符串的方法
	 * @param <T>          对象类型
	 * @return {@link Collector}
	 */
	public static <T> Collector<T, ?, String> joining(CharSequence delimiter,
			Function<T, ? extends CharSequence> toStringFunc) {
		return joining(delimiter, StringUtil.EMPTY, StringUtil.EMPTY, toStringFunc);
	}

	/**
	 * 提供任意对象的Join操作的{@link Collector}实现
	 *
	 * @param delimiter    分隔符
	 * @param prefix       前缀
	 * @param suffix       后缀
	 * @param toStringFunc 自定义指定对象转换为字符串的方法
	 * @param <T>          对象类型
	 * @return {@link Collector}
	 */
	public static <T> Collector<T, ?, String> joining(CharSequence delimiter, CharSequence prefix, CharSequence suffix,
			Function<T, ? extends CharSequence> toStringFunc) {
		return new SimpleCollector<>(() -> new StringJoiner(delimiter, prefix, suffix),
				(joiner, ele) -> joiner.add(toStringFunc.apply(ele)), StringJoiner::merge, StringJoiner::toString,
				Collections.emptySet());
	}
}
