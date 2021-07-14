package org.miser.core.io.file;

import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class PathUtilTest {

	@Test
	@Ignore
	public void copyFileTest() {
		PathUtil.copyFile(Paths.get("test/1595232240113.jpg"), Paths.get("test/1595232240113_copy.jpg"),
				StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
	}

	@Test
	@Ignore
	public void copyTest() {
		PathUtil.copy(Paths.get("Red2_LYY"), Paths.get("test/aaa/aaa.txt"));
	}

	@Test
	@Ignore
	public void copyContentTest() {
		PathUtil.copyContent(Paths.get("Red2_LYY"), Paths.get("test/aaa/"));
	}

	@Test
	@Ignore
	public void moveTest() {
		PathUtil.move(Paths.get("lombok.jar"), Paths.get("test/"), false);
	}

	@Test
	@Ignore
	public void getMimeTypeTest() {
		String mimeType = PathUtil.getMimeType(Paths.get("test/test.jpg"));
		Assert.assertEquals("image/jpeg", mimeType);

		mimeType = PathUtil.getMimeType(Paths.get("test/test.mov"));
		Assert.assertEquals("video/quicktime", mimeType);
	}
}
