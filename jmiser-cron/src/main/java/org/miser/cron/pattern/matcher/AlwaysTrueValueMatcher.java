package org.miser.cron.pattern.matcher;

import org.miser.core.util.StringUtil;

/**
 * 值匹配，始终返回<code>true</code>
 * @author Oliver
 *
 */
public class AlwaysTrueValueMatcher implements ValueMatcher{

	@Override
	public boolean match(Integer t) {
		return true;
	}
	
	@Override
	public String toString() {
		return StringUtil.format("[Matcher]: always true.");
	}
}
