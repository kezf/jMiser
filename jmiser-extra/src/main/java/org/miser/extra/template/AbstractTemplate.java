package org.miser.extra.template;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.StringWriter;
import java.util.Map;

import org.miser.core.io.FileUtil;
import org.miser.core.io.IOUtil;

/**
 * 抽象模板，提供将模板融合后写出到文件、返回字符串等方法
 * 
 * @author Oliver
 *
 */
public abstract class AbstractTemplate implements Template{
	
	@Override
	public void render(Map<?, ?> bindingMap, File file) {
		BufferedOutputStream out = null;
		try {
			out = FileUtil.getOutputStream(file);
			this.render(bindingMap, out);
		} finally {
			IOUtil.close(out);
		}
	}
	
	@Override
	public String render(Map<?, ?> bindingMap) {
		final StringWriter writer = new StringWriter();
		render(bindingMap, writer);
		return writer.toString();
	}
}
