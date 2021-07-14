package org.miser.core.convert.impl;

import java.io.InputStream;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.TimeZone;

import org.miser.core.convert.AbstractConverter;
import org.miser.core.convert.ConvertException;
import org.miser.core.io.IOUtil;
import org.miser.core.util.CharsetUtil;
import org.miser.core.util.XmlUtil;

/**
 * 字符串转换器，提供各种对象转换为字符串的逻辑封装
 *
 * @author Oliver
 */
public class StringConverter extends AbstractConverter<String> {
	private static final long serialVersionUID = 1L;

	@Override
	protected String convertInternal(Object value) {
		if (value instanceof TimeZone) {
			return ((TimeZone) value).getID();
		} else if (value instanceof org.w3c.dom.Node) {
			return XmlUtil.toStr((org.w3c.dom.Node) value);
		} else if (value instanceof Clob) {
			return clobToStr((Clob) value);
		} else if (value instanceof Blob) {
			return blobToStr((Blob) value);
		}

		// 其它情况
		return convertToStr(value);
	}

	/**
	 * Clob字段值转字符串
	 *
	 * @param clob {@link Clob}
	 * @return 字符串
	 * 
	 */
	private static String clobToStr(Clob clob) {
		Reader reader = null;
		try {
			reader = clob.getCharacterStream();
			return IOUtil.read(reader);
		} catch (SQLException e) {
			throw new ConvertException(e);
		} finally {
			IOUtil.close(reader);
		}
	}

	/**
	 * Blob字段值转字符串
	 *
	 * @param blob {@link Blob}
	 * @return 字符串
	 * 
	 */
	private static String blobToStr(Blob blob) {
		InputStream in = null;
		try {
			in = blob.getBinaryStream();
			return IOUtil.read(in, CharsetUtil.CHARSET_UTF_8);
		} catch (SQLException e) {
			throw new ConvertException(e);
		} finally {
			IOUtil.close(in);
		}
	}
}
