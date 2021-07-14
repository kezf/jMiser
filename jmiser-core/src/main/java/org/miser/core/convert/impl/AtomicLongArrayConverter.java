package org.miser.core.convert.impl;

import java.util.concurrent.atomic.AtomicLongArray;

import org.miser.core.convert.AbstractConverter;
import org.miser.core.convert.Convert;

/**
 * {@link AtomicLongArray}转换器
 *
 * @author Oliver
 * 
 */
public class AtomicLongArrayConverter extends AbstractConverter<AtomicLongArray> {
	private static final long serialVersionUID = 1L;

	@Override
	protected AtomicLongArray convertInternal(Object value) {
		return new AtomicLongArray(Convert.convert(long[].class, value));
	}

}
