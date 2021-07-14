package org.miser.cron;

import org.miser.core.util.StringUtil;

/**
 * 定时任务异常
 * @author Oliver
 */
public class CronException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public CronException(Throwable e) {
		super(e.getMessage(), e);
	}
	
	public CronException(String message) {
		super(message);
	}
	
	public CronException(String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params));
	}
	
	public CronException(Throwable throwable, String messageTemplate, Object... params) {
		super(StringUtil.format(messageTemplate, params), throwable);
	}
}
