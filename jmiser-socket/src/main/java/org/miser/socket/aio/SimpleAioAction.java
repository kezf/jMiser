package org.miser.socket.aio;

import java.nio.ByteBuffer;

import org.miser.socket.SocketRuntimeException;

/**
 * 简易IO信息处理类<br>
 * 简单实现了accept和failed事件
 * 
 * @author Oliver
 *
 */
public abstract class SimpleAioAction implements AioAction<ByteBuffer> {

	@Override
	public void accept(AioSession session) {
	}

	@Override
	public void failed(Throwable exc, AioSession session) {
		throw new SocketRuntimeException(exc);
	}
}
