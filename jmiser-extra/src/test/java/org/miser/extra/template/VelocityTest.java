package org.miser.extra.template;

import org.junit.Assert;
import org.junit.Test;
import org.miser.core.lang.Dict;
import org.miser.core.util.CharsetUtil;
import org.miser.extra.template.engine.velocity.VelocityEngine;

public class VelocityTest {

	@Test
	public void charsetTest(){
		final TemplateConfig config = new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH);
		config.setCustomEngine(VelocityEngine.class);
		config.setCharset(CharsetUtil.CHARSET_GBK);
		final TemplateEngine engine = TemplateUtil.createEngine(config);
		Template template = engine.getTemplate("velocity_test_gbk.vtl");
		String result = template.render(Dict.create().set("name", "test"));
		Assert.assertEquals("你好,test", result);
	}
}
