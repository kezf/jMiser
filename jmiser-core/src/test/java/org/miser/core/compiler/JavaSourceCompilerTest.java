package org.miser.core.compiler;

import java.io.File;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;
import org.miser.core.io.FileUtil;
import org.miser.core.util.ReflectUtil;
import org.miser.core.util.ZipUtil;

/**
 * Java源码编译器测试
 *
 * @author Oliver
 */
public class JavaSourceCompilerTest {

	/**
	 * 测试编译Java源码
	 */
	@Test
	public void testCompile() throws ClassNotFoundException {
		// 依赖A，编译B和C
		final File libFile = ZipUtil.zip(FileUtil.file("lib.jar"),
				new String[] { "a/A.class", "a/A$1.class", "a/A$InnerClass.class" },
				new InputStream[] { FileUtil.getInputStream("test-compile/a/A.class"),
						FileUtil.getInputStream("test-compile/a/A$1.class"),
						FileUtil.getInputStream("test-compile/a/A$InnerClass.class") });
		final ClassLoader classLoader = CompilerUtil.getCompiler(null).addSource(FileUtil.file("test-compile/b/B.java"))
				.addSource(FileUtil.file("test-compile/c/C.java")).addLibrary(libFile).compile();
		final Class<?> clazz = classLoader.loadClass("c.C");
		Object obj = ReflectUtil.newInstance(clazz);
		Assert.assertTrue(String.valueOf(obj).startsWith("c.C@"));
		FileUtil.del(libFile);
	}

}
