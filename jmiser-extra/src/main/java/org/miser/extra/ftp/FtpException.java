package org.miser.extra.ftp;

import org.miser.core.exceptions.ExceptionUtil;
import org.miser.core.util.StringUtil;

/**
 * Ftp异常
 * 
 * @author Oliver
 */
public class FtpException extends RuntimeException {
	private static final long serialVersionUID = -8490149159895201756L;

	public FtpException(Throwable e) {
		super(ExceptionUtil.getMessage(e), e);
	}

	public FtpException(String message) {
		super(message);
	}

	public FtpException(String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params));
	}

	public FtpException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public FtpException(Throwable throwable, String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params), throwable);
	}
}
