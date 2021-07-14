package org.miser.socket;

import org.miser.core.exceptions.ExceptionUtil;
import org.miser.core.util.StringUtil;

/**
 * Socket异常
 * 
 * @author Oliver
 */
public class SocketRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 8247610319171014183L;

	public SocketRuntimeException(Throwable e) {
		super(ExceptionUtil.getMessage(e), e);
	}

	public SocketRuntimeException(String message) {
		super(message);
	}

	public SocketRuntimeException(String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params));
	}

	public SocketRuntimeException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public SocketRuntimeException(Throwable throwable, String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params), throwable);
	}
}
