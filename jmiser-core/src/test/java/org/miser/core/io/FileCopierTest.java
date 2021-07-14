package org.miser.core.io;

import org.junit.Ignore;
import org.junit.Test;
import org.miser.core.io.file.FileCopier;

/**
 * 文件拷贝单元测试
 * 
 * @author Oliver
 *
 */
public class FileCopierTest {

	@Test
	@Ignore
	public void dirCopyTest() {
		FileCopier copier = FileCopier.create("Java", "e:/eclipse/eclipse2.zip");
		copier.copy();
	}

	@Test
	@Ignore
	public void dirCopyTest2() {
		// 测试带.的文件夹复制
		// FileCopier copier = FileCopier.create(".git", "/tmp");
		// copier.copy();

		// FileUtil.copy(".git", "/tmp", true);
	}

	@Test(expected = IORuntimeException.class)
	public void dirCopySubTest() {
		// 测试父目录复制到子目录报错
		FileCopier copier = FileCopier.create("workspace\\java\\.metadata", "workspace\\java\\.metadata\\temp");
		copier.copy();
	}

	@Test
	@Ignore
	public void copyFileToDirTest() {
		FileCopier copier = FileCopier.create("GReen_Soft/XshellXftpPortable.zip", "c:/hp/");
		copier.copy();
	}
}
