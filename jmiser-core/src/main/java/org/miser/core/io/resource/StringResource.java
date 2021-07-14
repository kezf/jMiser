package org.miser.core.io.resource;

import java.nio.charset.Charset;

import org.miser.core.util.CharsetUtil;

/**
 * 字符串资源，字符串做为资源
 *
 * @author Oliver
 * 
 * @see CharSequenceResource
 */
public class StringResource extends CharSequenceResource {
	private static final long serialVersionUID = 1L;

	/**
	 * 构造，使用UTF8编码
	 *
	 * @param data 资源数据
	 */
	public StringResource(String data) {
		super(data, null);
	}

	/**
	 * 构造，使用UTF8编码
	 *
	 * @param data 资源数据
	 * @param name 资源名称
	 */
	public StringResource(String data, String name) {
		super(data, name, CharsetUtil.CHARSET_UTF_8);
	}

	/**
	 * 构造
	 *
	 * @param data    资源数据
	 * @param name    资源名称
	 * @param charset 编码
	 */
	public StringResource(String data, String name, Charset charset) {
		super(data, name, charset);
	}
}
