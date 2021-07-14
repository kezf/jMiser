package org.miser.http.server.filter;

import com.sun.net.httpserver.Filter;

/**
 * 匿名简单过滤器，跳过了描述
 *
 * @author Oliver
 * 
 */
public abstract class SimpleFilter extends Filter {

	@Override
	public String description() {
		return "Anonymous Filter";
	}
}
