package org.miser.socket.aio;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;

import org.miser.core.io.IOUtil;
import org.miser.core.thread.ThreadFactoryBuilder;
import org.miser.socket.SocketConfig;
import org.miser.socket.SocketRuntimeException;

/**
 * Aio Socket客户端
 *
 * @author Oliver
 * 
 */
public class AioClient implements Closeable {

	private AioSession session;

	/**
	 * 构造
	 *
	 * @param host 服务器地址
	 * @param port 端口
	 */
	public AioClient(String host, int port, AioAction<ByteBuffer> action) {
		this(host, port, action, new SocketConfig());
	}

	/**
	 * 构造
	 *
	 * @param address 地址
	 * @param action  IO处理类
	 */
	public AioClient(String host, int port, AioAction<ByteBuffer> action, SocketConfig config) {
		init(createChannel(new InetSocketAddress(host, port), config.getThreadPoolSize()), action, config);
	}

	/**
	 * 构造
	 *
	 * @param channel {@link AsynchronousSocketChannel}
	 * @param action  IO处理类
	 * @param config  配置项
	 */
	public AioClient init(AsynchronousSocketChannel channel, AioAction<ByteBuffer> action, SocketConfig config) {
		this.session = new AioSession(channel, action, config);
		action.accept(this.session);
		return this;
	}

	/**
	 * 设置 Socket 的 Option 选项<br>
	 * 选项见：{@link java.net.StandardSocketOptions}
	 *
	 * @param <T>   选项泛型
	 * @param name  {@link SocketOption} 枚举
	 * @param value SocketOption参数
	 * @return this
	 * @throws IOException IO异常
	 */
	public <T> AioClient setOption(SocketOption<T> name, T value) throws IOException {
		this.session.getChannel().setOption(name, value);
		return this;
	}

	/**
	 * 获取IO处理器
	 *
	 * @return {@link AioAction}
	 */
	public AioAction<ByteBuffer> getAction() {
		return this.session.getAction();
	}

	/**
	 * 从服务端读取数据
	 *
	 * @return this
	 */
	public AioClient read() {
		this.session.read();
		return this;
	}

	/**
	 * 写数据到服务端
	 *
	 * @param data 数据
	 * @return this
	 */
	public AioClient write(ByteBuffer data) {
		this.session.write(data);
		return this;
	}

	/**
	 * 关闭客户端
	 */
	@Override
	public void close() {
		this.session.close();
	}

	// -------------------------------------------------------------------------------------
	// Private method start
	/**
	 * 初始化
	 *
	 * @param address  地址和端口
	 * @param poolSize 线程池大小
	 * @return this
	 */
	private static AsynchronousSocketChannel createChannel(InetSocketAddress address, int poolSize) {

		AsynchronousSocketChannel channel;
		try {
			AsynchronousChannelGroup group = AsynchronousChannelGroup.withFixedThreadPool(//
					poolSize, // 默认线程池大小
					ThreadFactoryBuilder.create().setNamePrefix("aio-client-socket-").build()//
			);
			channel = AsynchronousSocketChannel.open(group);
		} catch (IOException e) {
			throw new SocketRuntimeException(e);
		}

		try {
			channel.connect(address).get();
		} catch (InterruptedException | ExecutionException e) {
			IOUtil.close(channel);
			throw new SocketRuntimeException(e);
		}
		return channel;
	}
	// -------------------------------------------------------------------------------------
	// Private method end
}
