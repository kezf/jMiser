package org.miser.core.collection;

import java.util.Iterator;
import java.util.function.Function;

import org.miser.core.lang.Assert;

/**
 * 使用给定的转换函数，转换源{@link Iterator}为新类型的{@link Iterator}
 *
 * @param <F> 源元素类型
 * @param <T> 目标元素类型
 * @author Oliver
 * 
 */
public class TransIter<F, T> implements Iterator<T> {

	private final Iterator<? extends F> backingIterator;
	private final Function<? super F, ? extends T> func;

	/**
	 * 构造
	 *
	 * @param backingIterator 源{@link Iterator}
	 * @param func            转换函数
	 */
	public TransIter(Iterator<? extends F> backingIterator, Function<? super F, ? extends T> func) {
		this.backingIterator = Assert.notNull(backingIterator);
		this.func = Assert.notNull(func);
	}

	@Override
	public final boolean hasNext() {
		return backingIterator.hasNext();
	}

	@Override
	public final T next() {
		return func.apply(backingIterator.next());
	}

	@Override
	public final void remove() {
		backingIterator.remove();
	}
}
