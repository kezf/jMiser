package org.miser.log.dialect.console;

import org.miser.log.Log;
import org.miser.log.LogFactory;

/**
 * 利用System.out.println()打印日志
 * @author Oliver
 *
 */
public class ConsoleLogFactory extends LogFactory {
	
	public ConsoleLogFactory() {
		super("Console Logging");
	}

	@Override
	public Log createLog(String name) {
		return new ConsoleLog(name);
	}

	@Override
	public Log createLog(Class<?> clazz) {
		return new ConsoleLog(clazz);
	}

}
