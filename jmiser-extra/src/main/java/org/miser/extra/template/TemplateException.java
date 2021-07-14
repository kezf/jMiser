package org.miser.extra.template;

import org.miser.core.exceptions.ExceptionUtil;
import org.miser.core.util.StringUtil;

/**
 * 模板异常
 * 
 * @author Oliver
 */
public class TemplateException extends RuntimeException {
	private static final long serialVersionUID = 8247610319171014183L;

	public TemplateException(Throwable e) {
		super(ExceptionUtil.getMessage(e), e);
	}

	public TemplateException(String message) {
		super(message);
	}

	public TemplateException(String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params));
	}

	public TemplateException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public TemplateException(Throwable throwable, String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params), throwable);
	}
}
