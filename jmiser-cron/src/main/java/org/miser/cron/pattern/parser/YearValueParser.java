package org.miser.cron.pattern.parser;

/**
 * 年值处理
 * @author Oliver
 *
 */
public class YearValueParser extends SimpleValueParser{
	
	public YearValueParser() {
		super(1970, 2099);
	}

}
