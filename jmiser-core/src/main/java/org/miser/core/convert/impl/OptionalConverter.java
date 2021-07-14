package org.miser.core.convert.impl;

import java.util.Optional;

import org.miser.core.convert.AbstractConverter;

/**
 *
 * {@link Optional}对象转换器
 *
 * @author Oliver
 * 
 */
public class OptionalConverter extends AbstractConverter<Optional<?>> {
	private static final long serialVersionUID = 1L;

	@Override
	protected Optional<?> convertInternal(Object value) {
		return Optional.ofNullable(value);
	}

}
