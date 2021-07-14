package org.miser.extra.compress;

import org.miser.core.exceptions.ExceptionUtil;
import org.miser.core.util.StringUtil;

/**
 * 压缩解压异常语言异常
 * 
 * @author Oliver
 */
public class CompressException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CompressException(Throwable e) {
		super(ExceptionUtil.getMessage(e), e);
	}

	public CompressException(String message) {
		super(message);
	}

	public CompressException(String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params));
	}

	public CompressException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public CompressException(Throwable throwable, String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params), throwable);
	}
}
