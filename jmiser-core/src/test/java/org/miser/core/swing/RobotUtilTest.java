package org.miser.core.swing;

import org.junit.Ignore;
import org.junit.Test;

import org.miser.core.io.FileUtil;

public class RobotUtilTest {

	@Test
	@Ignore
	public void captureScreenTest() {
		RobotUtil.captureScreen(FileUtil.file("e:/screen.jpg"));
	}
}
