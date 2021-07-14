package org.miser.core.convert.impl;

import org.miser.core.convert.AbstractConverter;
import org.miser.core.util.BooleanUtil;
import org.miser.core.util.StringUtil;

/**
 * 字符转换器
 *
 * @author Oliver
 *
 */
public class CharacterConverter extends AbstractConverter<Character> {
	private static final long serialVersionUID = 1L;

	@Override
	protected Character convertInternal(Object value) {
		if (value instanceof Boolean) {
			return BooleanUtil.toCharacter((Boolean) value);
		} else {
			final String valueStr = convertToStr(value);
			if (StringUtil.isNotBlank(valueStr)) {
				return valueStr.charAt(0);
			}
		}
		return null;
	}

}
