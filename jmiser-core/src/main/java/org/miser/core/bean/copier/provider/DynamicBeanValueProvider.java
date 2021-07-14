package org.miser.core.bean.copier.provider;

import java.lang.reflect.Type;

import org.miser.core.bean.DynamicBean;
import org.miser.core.bean.copier.ValueProvider;
import org.miser.core.convert.Convert;

/**
 * DynaBean值提供者
 *
 * @author Oliver
 */
public class DynamicBeanValueProvider implements ValueProvider<String> {

	private final DynamicBean dynamicBean;
	private final boolean ignoreError;

	/**
	 * 构造
	 *
	 * @param dynamicBean 动态Bean
	 * @param ignoreError 是否忽略错误
	 */
	public DynamicBeanValueProvider(DynamicBean dynamicBean, boolean ignoreError) {
		this.dynamicBean = dynamicBean;
		this.ignoreError = ignoreError;
	}

	@Override
	public Object value(String key, Type valueType) {
		final Object value = dynamicBean.get(key);
		return Convert.convertWithCheck(valueType, value, null, this.ignoreError);
	}

	@Override
	public boolean containsKey(String key) {
		return dynamicBean.containsProp(key);
	}

}
