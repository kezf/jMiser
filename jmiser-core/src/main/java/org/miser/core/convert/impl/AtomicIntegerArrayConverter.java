package org.miser.core.convert.impl;

import java.util.concurrent.atomic.AtomicIntegerArray;

import org.miser.core.convert.AbstractConverter;
import org.miser.core.convert.Convert;

/**
 * {@link AtomicIntegerArray}转换器
 *
 * @author Oliver
 * 
 */
public class AtomicIntegerArrayConverter extends AbstractConverter<AtomicIntegerArray> {
	private static final long serialVersionUID = 1L;

	@Override
	protected AtomicIntegerArray convertInternal(Object value) {
		return new AtomicIntegerArray(Convert.convert(int[].class, value));
	}

}
