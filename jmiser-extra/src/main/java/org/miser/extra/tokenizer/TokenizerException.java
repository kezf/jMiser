package org.miser.extra.tokenizer;

import org.miser.core.exceptions.ExceptionUtil;
import org.miser.core.util.StringUtil;

/**
 * 分词异常
 * 
 * @author Oliver
 */
public class TokenizerException extends RuntimeException {
	private static final long serialVersionUID = 8074865854534335463L;

	public TokenizerException(Throwable e) {
		super(ExceptionUtil.getMessage(e), e);
	}

	public TokenizerException(String message) {
		super(message);
	}

	public TokenizerException(String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params));
	}

	public TokenizerException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public TokenizerException(Throwable throwable, String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params), throwable);
	}
}
