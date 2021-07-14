package org.miser.extra.qrcode;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.miser.core.codec.Base64;
import org.miser.core.io.FileUtil;
import org.miser.core.lang.Console;

/**
 * 二维码工具类单元测试
 *
 * @author Oliver
 *
 */
public class QrCodeUtilTest {

	@Test
	public void generateTest() {
		final BufferedImage image = QrCodeUtil.generate("https://miser.org/", 300, 300);
		Assert.assertNotNull(image);
	}

	@Test
	@Ignore
	public void generateCustomTest() {
		QrConfig config = new QrConfig();
		config.setMargin(0);
		config.setForeColor(Color.CYAN);
		// 背景色透明
		config.setBackColor(null);
		config.setErrorCorrection(ErrorCorrectionLevel.H);
		QrCodeUtil.generate("https://miser.org/", config, FileUtil.file("d:/qrcodeCustom.png"));
	}

	@Test
	@Ignore
	public void generateWithLogoTest() {
		QrCodeUtil.generate(//
				"http://miser.org/", //
				QrConfig.create().setImg("e:/pic/face.jpg"), //
				FileUtil.file("e:/qrcodeWithLogo.jpg"));
	}

	@Test
	@Ignore
	public void decodeTest() {
		String decode = QrCodeUtil.decode(FileUtil.file("e:/pic/qr.png"));
		Console.log(decode);
	}

	@Test
	@Ignore
	public void decodeTest2() {
		// 条形码
		String decode = QrCodeUtil.decode(FileUtil.file("d:/test/90.png"));
		Console.log(decode);
	}

	@Test
	@Ignore
	public void generateAsBase64Test(){
		String base64 = QrCodeUtil.generateAsBase64("http://miser.org/", new QrConfig(400, 400), "png");
		System.out.println(base64);

		byte[] bytes = FileUtil.readBytes(
			new File("d:/test/qr.png"));
		String encode = Base64.encode(bytes);
		String base641 = QrCodeUtil.generateAsBase64("http://miser.org/", new QrConfig(400, 400), "png", encode);
		System.out.println(base641);

	}

}
