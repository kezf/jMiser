package org.miser.extra.pinyin;

import org.miser.core.exceptions.ExceptionUtil;
import org.miser.core.util.StringUtil;

/**
 * 模板异常
 * 
 * @author Oliver
 */
public class PinyinException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PinyinException(Throwable e) {
		super(ExceptionUtil.getMessage(e), e);
	}

	public PinyinException(String message) {
		super(message);
	}

	public PinyinException(String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params));
	}

	public PinyinException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public PinyinException(Throwable throwable, String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params), throwable);
	}
}
