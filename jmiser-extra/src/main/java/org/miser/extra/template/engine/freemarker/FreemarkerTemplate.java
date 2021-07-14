package org.miser.extra.template.engine.freemarker;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.util.Map;

import org.miser.core.io.IORuntimeException;
import org.miser.core.io.IOUtil;
import org.miser.extra.template.AbstractTemplate;
import org.miser.extra.template.TemplateException;

/**
 * Freemarker模板实现
 * 
 * @author Oliver
 */
public class FreemarkerTemplate extends AbstractTemplate implements Serializable{
	private static final long serialVersionUID = -8157926902932567280L;

	freemarker.template.Template rawTemplate;
	
	/**
	 * 包装Freemarker模板
	 * 
	 * @param beetlTemplate Beetl的模板对象 {@link freemarker.template.Template}
	 * @return {@link FreemarkerTemplate}
	 */
	public static FreemarkerTemplate wrap(freemarker.template.Template beetlTemplate) {
		return (null == beetlTemplate) ? null : new FreemarkerTemplate(beetlTemplate);
	}
	
	/**
	 * 构造
	 * 
	 * @param freemarkerTemplate Beetl的模板对象 {@link freemarker.template.Template}
	 */
	public FreemarkerTemplate(freemarker.template.Template freemarkerTemplate) {
		this.rawTemplate = freemarkerTemplate;
	}

	@Override
	public void render(Map<?, ?> bindingMap, Writer writer) {
		try {
			rawTemplate.process(bindingMap, writer);
		} catch (freemarker.template.TemplateException e) {
			throw new TemplateException(e);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}

	@Override
	public void render(Map<?, ?> bindingMap, OutputStream out) {
		render(bindingMap, IOUtil.getWriter(out, this.rawTemplate.getEncoding()));
	}

}
