package org.miser.extra.expression;

import org.miser.core.exceptions.ExceptionUtil;
import org.miser.core.util.StringUtil;

/**
 * 表达式语言异常
 * 
 * @author Oliver
 */
public class ExpressionException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ExpressionException(Throwable e) {
		super(ExceptionUtil.getMessage(e), e);
	}

	public ExpressionException(String message) {
		super(message);
	}

	public ExpressionException(String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params));
	}

	public ExpressionException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public ExpressionException(Throwable throwable, String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params), throwable);
	}
}
