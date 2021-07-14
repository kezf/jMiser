package org.miser.log.dialect.logtube;

import org.miser.log.Log;
import org.miser.log.LogFactory;

/**
 * <a href="https://github.com/logtube/logtube-java">LogTube</a> log. 封装<br>
 *
 * @author Oliver
 * 
 */
public class LogTubeLogFactory extends LogFactory {

	public LogTubeLogFactory() {
		super("LogTube");
		checkLogExist(io.github.logtube.Logtube.class);
	}

	@Override
	public Log createLog(String name) {
		return new LogTubeLog(name);
	}

	@Override
	public Log createLog(Class<?> clazz) {
		return new LogTubeLog(clazz);
	}

}
