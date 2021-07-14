package org.miser.aop.proxy;

import org.miser.aop.aspects.Aspect;
import org.miser.aop.interceptor.CglibInterceptor;
import net.sf.cglib.proxy.Enhancer;

/**
 * 基于Cglib的切面代理工厂
 *
 * @author Oliver
 *
 */
public class CglibProxyFactory extends ProxyFactory{
	private static final long serialVersionUID = 1L;

	@Override
	@SuppressWarnings("unchecked")
	public <T> T proxy(T target, Aspect aspect) {
		final Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(target.getClass());
		enhancer.setCallback(new CglibInterceptor(target, aspect));
		return (T) enhancer.create();
	}

}
