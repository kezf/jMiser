package org.miser.core.compiler;

import org.miser.core.exceptions.ExceptionUtil;
import org.miser.core.util.StringUtil;

/**
 * 编译异常
 *
 * @author Oliver
 * 
 */
public class CompilerException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CompilerException(Throwable e) {
		super(ExceptionUtil.getMessage(e), e);
	}

	public CompilerException(String message) {
		super(message);
	}

	public CompilerException(String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params));
	}

	public CompilerException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public CompilerException(Throwable throwable, String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params), throwable);
	}
}
