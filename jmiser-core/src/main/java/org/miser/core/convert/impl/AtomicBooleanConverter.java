package org.miser.core.convert.impl;

import java.util.concurrent.atomic.AtomicBoolean;

import org.miser.core.convert.AbstractConverter;
import org.miser.core.util.BooleanUtil;

/**
 * {@link AtomicBoolean}转换器
 *
 * @author Oliver
 * 
 */
public class AtomicBooleanConverter extends AbstractConverter<AtomicBoolean> {
	private static final long serialVersionUID = 1L;

	@Override
	protected AtomicBoolean convertInternal(Object value) {
		if (value instanceof Boolean) {
			return new AtomicBoolean((Boolean) value);
		}
		final String valueStr = convertToStr(value);
		return new AtomicBoolean(BooleanUtil.toBoolean(valueStr));
	}

}
