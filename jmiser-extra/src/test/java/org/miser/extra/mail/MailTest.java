package org.miser.extra.mail;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.miser.core.io.FileUtil;

/**
 * 邮件发送测试
 * 
 * @author Oliver
 *
 */
public class MailTest {

	@Test
	@Ignore
	public void sendWithFileTest() {
		MailUtil.send("test@foxmail.com", "测试", "<h1>邮件来自测试</h1>", true, FileUtil.file("测试附件文本.txt"));
	}

	@Test
	@Ignore
	public void sendWithLongNameFileTest() {
		// 附件名长度大于60时的测试
		MailUtil.send("test@foxmail.com", "测试", "<h1>邮件来自测试</h1>", true,
				FileUtil.file("6-test一阶段平台建设周报2018.3.12-3.16.xlsx"));
	}

	@Test
	@Ignore
	public void sendWithImageTest() {
		Map<String, InputStream> map = new HashMap<>();
		map.put("testImage", FileUtil.getInputStream("f:/test/me.png"));
		MailUtil.sendHtml("test@foxmail.com", "测试", "<h1>邮件来自测试</h1><img src=\"cid:testImage\" />", map);
	}

	@Test
	@Ignore
	public void sendHtmlTest() {
		MailUtil.send("test@foxmail.com", "测试", "<h1>邮件来自测试</h1>", true);
	}

	@Test
	@Ignore
	public void sendByAccountTest() {
		MailAccount account = new MailAccount();
		account.setHost("smtp.yeah.net");
		account.setPort(465);
		account.setSslEnable(true);
		account.setFrom("test@yeah.net");
		account.setUser("test");
		account.setPass("q1w2e3");
		MailUtil.send(account, "test@foxmail.com", "测试", "<h1>邮件来自测试</h1>", true);
	}

	@Test
	public void mailAccountTest() {
		MailAccount account = new MailAccount();
		account.setFrom("test@yeah.net");
		account.setDebug(true);
		account.defaultIfEmpty();
		Properties props = account.getSmtpProps();
		Assert.assertEquals("true", props.getProperty("mail.debug"));
	}
}
