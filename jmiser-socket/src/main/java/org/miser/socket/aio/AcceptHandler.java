package org.miser.socket.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import org.miser.socket.SocketConfig;
import org.miser.socket.SocketRuntimeException;

/**
 * 接入完成回调，单例使用
 * 
 * @author Oliver
 */
public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AioServer> {

	@Override
	public void completed(AsynchronousSocketChannel channel, AioServer server) {
		// 继续等待接入（异步）
		server.accept();

		final AioAction<ByteBuffer> action = server.action;
		final SocketConfig config = server.config;
		// 创建Session会话
		final AioSession session = new AioSession(channel, action, config);
		// 处理请求接入（同步）
		action.accept(session);

		// 处理读（异步）
		session.read();
	}

	@Override
	public void failed(Throwable exc, AioServer server) {
		throw new SocketRuntimeException(exc);
	}

}
