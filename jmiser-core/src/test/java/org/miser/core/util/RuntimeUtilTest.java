package org.miser.core.util;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.miser.core.lang.Console;

/**
 * 命令行单元测试
 * 
 * @author Oliver
 *
 */
public class RuntimeUtilTest {

	@Test
	@Ignore
	public void execTest() {
		String str = RuntimeUtil.execForStr("ipconfig");
		Console.log(str);
	}

	@Test
	@Ignore
	public void execCmdTest() {
		String str = RuntimeUtil.execForStr("cmd /c dir");
		Console.log(str);
	}

	@Test
	@Ignore
	public void execCmdTest2() {
		String str = RuntimeUtil.execForStr("cmd /c", "cd \"C:\\Program Files (x86)\"", "chdir");
		Console.log(str);
	}

	@Test
	public void getUsableMemoryTest() {
		Assert.assertTrue(RuntimeUtil.getUsableMemory() > 0);
	}
}
