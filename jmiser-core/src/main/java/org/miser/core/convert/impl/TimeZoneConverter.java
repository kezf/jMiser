package org.miser.core.convert.impl;

import java.util.TimeZone;

import org.miser.core.convert.AbstractConverter;

/**
 * TimeZone转换器
 * 
 * @author Oliver
 *
 */
public class TimeZoneConverter extends AbstractConverter<TimeZone> {
	private static final long serialVersionUID = 1L;

	@Override
	protected TimeZone convertInternal(Object value) {
		return TimeZone.getTimeZone(convertToStr(value));
	}

}
