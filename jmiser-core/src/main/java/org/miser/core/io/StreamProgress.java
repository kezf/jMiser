package org.miser.core.io;

/**
 * Stream进度条
 * 
 * @author Oliver
 *
 */
public interface StreamProgress {

	/**
	 * 开始
	 */
	void start();

	/**
	 * 进行中
	 * 
	 * @param progressSize 已经进行的大小
	 */
	void progress(long progressSize);

	/**
	 * 结束
	 */
	void finish();
}
