package org.miser.aop.proxy;

import org.miser.aop.ProxyUtil;
import org.miser.aop.aspects.Aspect;
import org.miser.aop.interceptor.JdkInterceptor;

/**
 * JDK实现的切面代理
 *
 * @author Oliver
 */
public class JdkProxyFactory extends ProxyFactory {
	private static final long serialVersionUID = 1L;

	@Override
	public <T> T proxy(T target, Aspect aspect) {
		return ProxyUtil.newProxyInstance(//
				target.getClass().getClassLoader(), //
				new JdkInterceptor(target, aspect), //
				target.getClass().getInterfaces());
	}
}
