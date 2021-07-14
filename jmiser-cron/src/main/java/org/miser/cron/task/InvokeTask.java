package org.miser.cron.task;

import org.miser.core.exceptions.UtilException;
import org.miser.core.util.ClassLoaderUtil;
import org.miser.core.util.ClassUtil;
import org.miser.core.util.ReflectUtil;
import org.miser.core.util.StringUtil;
import org.miser.cron.CronException;

import java.lang.reflect.Method;

/**
 * 反射执行任务<br>
 * 通过传入类名#方法名，通过反射执行相应的方法<br>
 * 如果是静态方法直接执行，如果是对象方法，需要类有默认的构造方法。
 * 
 * @author Oliver
 *
 */
public class InvokeTask implements Task{

	private final Object obj;
	private final Method method;
	
	/**
	 * 构造
	 * @param classNameWithMethodName 类名与方法名的字符串表示，方法名和类名使用#隔开或者.隔开
	 */
	public InvokeTask(String classNameWithMethodName) {
		int splitIndex = classNameWithMethodName.lastIndexOf('#');
		if(splitIndex <= 0){
			splitIndex = classNameWithMethodName.lastIndexOf('.');
		}
		if (splitIndex <= 0) {
			throw new UtilException("Invalid classNameWithMethodName [{}]!", classNameWithMethodName);
		}

		//类
		final String className = classNameWithMethodName.substring(0, splitIndex);
		if(StringUtil.isBlank(className)) {
			throw new IllegalArgumentException("Class name is blank !");
		}
		final Class<?> clazz = ClassLoaderUtil.loadClass(className);
		if(null == clazz) {
			throw new IllegalArgumentException("Load class with name of [" + className + "] fail !");
		}
		this.obj = ReflectUtil.newInstanceIfPossible(clazz);
		
		//方法
		final String methodName = classNameWithMethodName.substring(splitIndex + 1);
		if(StringUtil.isBlank(methodName)) {
			throw new IllegalArgumentException("Method name is blank !");
		}
		this.method = ClassUtil.getPublicMethod(clazz, methodName);
		if(null == this.method) {
			throw new IllegalArgumentException("No method with name of [" + methodName + "] !");
		}
	}

	@Override
	public void execute() {
		try {
			ReflectUtil.invoke(this.obj, this.method);
		} catch (UtilException e) {
			throw new CronException(e.getCause());
		}
	}
}
