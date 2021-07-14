package org.miser.core.img;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class ImgUtilTest {

	@Test
	public void toHexTest() {
		final String s = ImgUtil.toHex(Color.RED);
		Assert.assertEquals("#FF0000", s);
	}

	@Test
	@Ignore
	public void backgroundRemovalTest() {
		// 图片 背景 换成 透明的
		ImgUtil.backgroundRemoval("test/617180969474805871.jpg", "test/2.jpg", 10);

		// 图片 背景 换成 红色的
		ImgUtil.backgroundRemoval(new File("test/617180969474805871.jpg"), new File("test/3.jpg"), new Color(200, 0, 0),
				10);
	}

	@Test
	public void getMainColor() throws MalformedURLException {
		BufferedImage read = ImgUtil.read(new URL("https://pic2.zhimg.com/v2-94f5552f2b142ff575306850c5bab65d_b.png"));
		String mainColor = ImgUtil.getMainColor(read, new int[] { 64, 84, 116 });
		System.out.println(mainColor);
	}

}
