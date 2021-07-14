package org.miser.core.io;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.miser.core.lang.Console;

/**
 * {@link FileUtil} 单元测试类
 *
 * @author Oliver
 */
public class FileUtilTest {

	@Test(expected = IllegalArgumentException.class)
	public void fileTest() {
		File file = FileUtil.file("aaa", "bbb");
		Assert.assertNotNull(file);

		// 构建目录中出现非子目录抛出异常
		FileUtil.file(file, "../ccc");
	}

	@Test
	public void getAbsolutePathTest() {
		String absolutePath = FileUtil.getAbsolutePath("LICENSE-junit.txt");
		Assert.assertNotNull(absolutePath);
		String absolutePath2 = FileUtil.getAbsolutePath(absolutePath);
		Assert.assertNotNull(absolutePath2);
		Assert.assertEquals(absolutePath, absolutePath2);

		String path = FileUtil.getAbsolutePath("中文.xml");
		Assert.assertTrue(path.contains("中文.xml"));

		path = FileUtil.getAbsolutePath("/");
		Assert.assertEquals("/", path);
	}

	@Test
	public void copyTest() {
		File srcFile = FileUtil.file("logo.jpg");
		File destFile = FileUtil.file("logo.copy.jpg");

		FileUtil.copy(srcFile, destFile, true);

		Assert.assertTrue(destFile.exists());
		Assert.assertEquals(srcFile.length(), destFile.length());
	}

	@Test
	public void equalsTest() {
		// 源文件和目标文件都不存在
		File srcFile = FileUtil.file("test.jpg");
		File destFile = FileUtil.file("test.jpg");

		boolean equals = FileUtil.equals(srcFile, destFile);
		Assert.assertTrue(equals);

		// 源文件存在，目标文件不存在
		File srcFile1 = FileUtil.file("logo.jpg");
		File destFile1 = FileUtil.file("test.jpg");

		boolean notEquals = FileUtil.equals(srcFile1, destFile1);
		Assert.assertFalse(notEquals);
	}

	@Test
	public void normalizeTest() {
		Assert.assertEquals("/foo/", FileUtil.normalize("/foo//"));
		Assert.assertEquals("/foo/", FileUtil.normalize("/foo/./"));
		Assert.assertEquals("/bar", FileUtil.normalize("/foo/../bar"));
		Assert.assertEquals("/bar/", FileUtil.normalize("/foo/../bar/"));
		Assert.assertEquals("/baz", FileUtil.normalize("/foo/../bar/../baz"));
		Assert.assertEquals("/", FileUtil.normalize("/../"));
		Assert.assertEquals("foo", FileUtil.normalize("foo/bar/.."));
		Assert.assertEquals("bar", FileUtil.normalize("foo/../../bar"));
		Assert.assertEquals("bar", FileUtil.normalize("foo/../bar"));
		Assert.assertEquals("/server/bar", FileUtil.normalize("//server/foo/../bar"));
		Assert.assertEquals("/bar", FileUtil.normalize("//server/../bar"));
		Assert.assertEquals("C:/bar", FileUtil.normalize("C:\\foo\\..\\bar"));
		Assert.assertEquals("C:/bar", FileUtil.normalize("C:\\..\\bar"));
		Assert.assertEquals("bar", FileUtil.normalize("../../bar"));
		Assert.assertEquals("C:/bar", FileUtil.normalize("/C:/bar"));
		Assert.assertEquals("C:", FileUtil.normalize("C:"));
		Assert.assertEquals("\\/192.168.1.1/Share/", FileUtil.normalize("\\\\192.168.1.1\\Share\\"));
	}

	@Test
	public void normalizeBlankTest() {
		Assert.assertEquals("C:/aaa ", FileUtil.normalize("C:\\aaa "));
	}

	@Test
	public void normalizeHomePathTest() {
		String home = FileUtil.getUserHomePath().replace('\\', '/');
		Assert.assertEquals(home + "/bar/", FileUtil.normalize("~/foo/../bar/"));
	}

	@Test
	public void normalizeHomePathTest2() {
		String home = FileUtil.getUserHomePath().replace('\\', '/');
		// 多个~应该只替换开头的
		Assert.assertEquals(home + "/~bar/", FileUtil.normalize("~/foo/../~bar/"));
	}

	@Test
	public void normalizeClassPathTest() {
		Assert.assertEquals("", FileUtil.normalize("classpath:"));
	}

	@Test
	public void doubleNormalizeTest() {
		String normalize = FileUtil.normalize("/aa/b:/c");
		String normalize2 = FileUtil.normalize(normalize);
		Assert.assertEquals("/aa/b:/c", normalize);
		Assert.assertEquals(normalize, normalize2);
	}

	@Test
	public void subPathTest() {
		Path path = Paths.get("/aaa/bbb/ccc/ddd/eee/fff");

		Path subPath = FileUtil.subPath(path, 5, 4);
		Assert.assertEquals("eee", subPath.toString());
		subPath = FileUtil.subPath(path, 0, 1);
		Assert.assertEquals("aaa", subPath.toString());
		subPath = FileUtil.subPath(path, 1, 0);
		Assert.assertEquals("aaa", subPath.toString());

		// 负数
		subPath = FileUtil.subPath(path, -1, 0);
		Assert.assertEquals("aaa/bbb/ccc/ddd/eee", subPath.toString().replace('\\', '/'));
		subPath = FileUtil.subPath(path, -1, Integer.MAX_VALUE);
		Assert.assertEquals("fff", subPath.toString());
		subPath = FileUtil.subPath(path, -1, path.getNameCount());
		Assert.assertEquals("fff", subPath.toString());
		subPath = FileUtil.subPath(path, -2, -3);
		Assert.assertEquals("ddd", subPath.toString());
	}

	@Test
	public void subPathTest2() {
		String subPath = FileUtil.subPath("aaa/bbb/", "aaa/bbb/ccc/");
		Assert.assertEquals("ccc/", subPath);

		subPath = FileUtil.subPath("aaa/bbb", "aaa/bbb/ccc/");
		Assert.assertEquals("ccc/", subPath);

		subPath = FileUtil.subPath("aaa/bbb", "aaa/bbb/ccc/test.txt");
		Assert.assertEquals("ccc/test.txt", subPath);

		subPath = FileUtil.subPath("aaa/bbb/", "aaa/bbb/ccc");
		Assert.assertEquals("ccc", subPath);

		subPath = FileUtil.subPath("aaa/bbb", "aaa/bbb/ccc");
		Assert.assertEquals("ccc", subPath);

		subPath = FileUtil.subPath("aaa/bbb", "aaa/bbb");
		Assert.assertEquals("", subPath);

		subPath = FileUtil.subPath("aaa/bbb/", "aaa/bbb");
		Assert.assertEquals("", subPath);
	}

	@Test
	public void getPathEle() {
		Path path = Paths.get("/aaa/bbb/ccc/ddd/eee/fff");

		Path ele = FileUtil.getPathEle(path, -1);
		Assert.assertEquals("fff", ele.toString());
		ele = FileUtil.getPathEle(path, 0);
		Assert.assertEquals("aaa", ele.toString());
		ele = FileUtil.getPathEle(path, -5);
		Assert.assertEquals("bbb", ele.toString());
		ele = FileUtil.getPathEle(path, -6);
		Assert.assertEquals("aaa", ele.toString());
	}

	@Test
	public void listFileNamesTest() {
		List<String> names = FileUtil.listFileNames("classpath:");
		Assert.assertTrue(names.contains("logo.jpg"));

		names = FileUtil.listFileNames("");
		Assert.assertTrue(names.contains("logo.jpg"));

		names = FileUtil.listFileNames(".");
		Assert.assertTrue(names.contains("logo.jpg"));
	}

	@Test
	@Ignore
	public void listFileNamesInJarTest() {
		List<String> names = FileUtil.listFileNames("test/jmiser-core-5.1.0.jar!/org/miser/core/util ");
		for (String name : names) {
			Console.log(name);
		}
	}

	@Test
	public void getParentTest() {
		// 只在Windows下测试
		if (FileUtil.isWindows()) {
			File parent = FileUtil.getParent(FileUtil.file("aaa/bbb/cc/ddd"), 0);
			Assert.assertEquals(FileUtil.file("aaa\\bbb\\cc\\ddd"), parent);

			parent = FileUtil.getParent(FileUtil.file("aaa/bbb/cc/ddd"), 1);
			Assert.assertEquals(FileUtil.file("aaa\\bbb\\cc"), parent);

			parent = FileUtil.getParent(FileUtil.file("aaa/bbb/cc/ddd"), 2);
			Assert.assertEquals(FileUtil.file("aaa\\bbb"), parent);

			parent = FileUtil.getParent(FileUtil.file("aaa/bbb/cc/ddd"), 4);
			Assert.assertEquals(FileUtil.file(""), parent);

			parent = FileUtil.getParent(FileUtil.file("aaa/bbb/cc/ddd"), 5);
			Assert.assertNull(parent);

			parent = FileUtil.getParent(FileUtil.file("aaa/bbb/cc/ddd"), 10);
			Assert.assertNull(parent);
		}
	}

	@Test
	public void lastIndexOfSeparatorTest() {
		String dir = "aaa\\bbb\\cc\\ddd";
		int index = FileUtil.lastIndexOfSeparator(dir);
		Assert.assertEquals(10, index);

		String file = "ddd.jpg";
		int index2 = FileUtil.lastIndexOfSeparator(file);
		Assert.assertEquals(-1, index2);
	}

	@Test
	public void getNameTest() {
		String path = "aaa\\bbb\\cc\\ddd\\";
		String name = FileUtil.getName(path);
		Assert.assertEquals("ddd", name);

		path = "aaa\\bbb\\cc\\ddd.jpg";
		name = FileUtil.getName(path);
		Assert.assertEquals("ddd.jpg", name);
	}

	@Test
	public void mainNameTest() {
		String path = "aaa\\bbb\\cc\\ddd\\";
		String mainName = FileUtil.mainName(path);
		Assert.assertEquals("ddd", mainName);

		path = "aaa\\bbb\\cc\\ddd";
		mainName = FileUtil.mainName(path);
		Assert.assertEquals("ddd", mainName);

		path = "aaa\\bbb\\cc\\ddd.jpg";
		mainName = FileUtil.mainName(path);
		Assert.assertEquals("ddd", mainName);
	}

	@Test
	public void extNameTest() {
		String path = "aaa\\bbb\\cc\\ddd\\";
		String mainName = FileUtil.extName(path);
		Assert.assertEquals("", mainName);

		path = "aaa\\bbb\\cc\\ddd";
		mainName = FileUtil.extName(path);
		Assert.assertEquals("", mainName);

		path = "aaa\\bbb\\cc\\ddd.jpg";
		mainName = FileUtil.extName(path);
		Assert.assertEquals("jpg", mainName);
	}

	@Test
	public void getWebRootTest() {
		File webRoot = FileUtil.getWebRoot();
		Assert.assertNotNull(webRoot);
		Assert.assertEquals("jmiser-core", webRoot.getName());
	}

	@Test
	public void getMimeTypeTest() {
		String mimeType = FileUtil.getMimeType("test2Write.jpg");
		Assert.assertEquals("image/jpeg", mimeType);

		mimeType = FileUtil.getMimeType("test2Write.html");
		Assert.assertEquals("text/html", mimeType);

		mimeType = FileUtil.getMimeType("main.css");
		Assert.assertEquals("text/css", mimeType);

		mimeType = FileUtil.getMimeType("test.js");
		Assert.assertEquals("application/x-javascript", mimeType);
	}

	@Test
	public void isSubTest() {
		File file = new File("test");
		File file2 = new File("test2/aaa");
		Assert.assertFalse(FileUtil.isSub(file, file2));
	}

	@Test
	public void isSubRelativeTest() {
		File file = new File("..");
		File file2 = new File(".");
		Assert.assertTrue(FileUtil.isSub(file, file2));
	}
}
