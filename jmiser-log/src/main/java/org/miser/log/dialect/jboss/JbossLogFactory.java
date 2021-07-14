package org.miser.log.dialect.jboss;

import org.miser.log.Log;
import org.miser.log.LogFactory;

/**
 * <a href="https://github.com/jboss-logging">Jboss-Logging</a> log.
 * 
 * @author Oliver
 * 
 */
public class JbossLogFactory extends LogFactory {

	/**
	 * 构造
	 */
	public JbossLogFactory() {
		super("JBoss Logging");
		checkLogExist(org.jboss.logging.Logger.class);
	}

	@Override
	public Log createLog(String name) {
		return new JbossLog(name);
	}

	@Override
	public Log createLog(Class<?> clazz) {
		return new JbossLog(clazz);
	}

}
