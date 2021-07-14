package org.miser;

import java.util.Set;

import org.miser.core.lang.ConsoleTable;
import org.miser.core.util.ClassUtil;
import org.miser.core.util.StringUtil;

/**
 * <p>
 * 这是一个小而全的Java工具类库，通过静态方法封装，降低相关API的学习成本，提高工作效率，使Java拥有函数式语言般的优雅。
 * </p>
 *
 * @author Oliver
 */
public class Jmiser {

	public static final String AUTHOR = "Oliver";

	private Jmiser() {
	}

	/**
	 * 显示所有的工具类
	 *
	 * @return 工具类名集合
	 */
	public static Set<Class<?>> getAllUtils() {
		return ClassUtil.scanPackage("org.miser",
				(clazz) -> (false == clazz.isInterface()) && StringUtil.endWith(clazz.getSimpleName(), "Util"));
	}

	/**
	 * 控制台打印所有工具类
	 */
	public static void printAllUtils() {
		final Set<Class<?>> allUtils = getAllUtils();
		final ConsoleTable consoleTable = ConsoleTable.create().addHeader("工具类名", "所在包");
		for (Class<?> clazz : allUtils) {
			consoleTable.addBody(clazz.getSimpleName(), clazz.getPackage().getName());
		}
		consoleTable.print();
	}
}
