package org.miser.core.convert;

import org.miser.core.exceptions.ExceptionUtil;
import org.miser.core.util.StringUtil;

/**
 * 转换异常
 * 
 * @author Oliver
 */
public class ConvertException extends RuntimeException {
	private static final long serialVersionUID = 4730597402855274362L;

	public ConvertException(Throwable e) {
		super(ExceptionUtil.getMessage(e), e);
	}

	public ConvertException(String message) {
		super(message);
	}

	public ConvertException(String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params));
	}

	public ConvertException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public ConvertException(Throwable throwable, String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params), throwable);
	}
}
