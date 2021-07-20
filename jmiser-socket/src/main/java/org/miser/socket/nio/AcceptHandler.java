package org.miser.socket.nio;

import java.io.IOException;
import java.nio.channels.CompletionHandler;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.miser.socket.SocketRuntimeException;

/**
 * 接入完成回调，单例使用
 * 
 * @author Oliver
 */
public class AcceptHandler implements CompletionHandler<ServerSocketChannel, NioServer> {

	@Override
	public void completed(ServerSocketChannel serverSocketChannel, NioServer nioServer) {
		SocketChannel socketChannel;
		try {
			// 获取连接到此服务器的客户端通道
			socketChannel = serverSocketChannel.accept();
		} catch (IOException e) {
			throw new SocketRuntimeException(e);
		}

		// SocketChannel通道的可读事件注册到Selector中
		NioUtil.registerChannel(nioServer.getSelector(), socketChannel, Operation.READ);
	}

	@Override
	public void failed(Throwable exc, NioServer nioServer) {
		throw new SocketRuntimeException(exc);
	}

}
