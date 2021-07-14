package org.miser.jwt;

import org.miser.core.exceptions.ExceptionUtil;
import org.miser.core.util.StringUtil;

/**
 * JWT异常
 *
 * @author Oliver
 * 
 */
public class JWTException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public JWTException(Throwable e) {
		super(ExceptionUtil.getMessage(e), e);
	}

	public JWTException(String message) {
		super(message);
	}

	public JWTException(String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params));
	}

	public JWTException(String message, Throwable cause) {
		super(message, cause);
	}

	public JWTException(Throwable throwable, String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params), throwable);
	}
}
