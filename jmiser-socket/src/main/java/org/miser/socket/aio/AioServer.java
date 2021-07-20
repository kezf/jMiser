package org.miser.socket.aio;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;

import org.miser.core.io.IOUtil;
import org.miser.core.thread.ThreadFactoryBuilder;
import org.miser.core.thread.ThreadUtil;
import org.miser.socket.SocketConfig;
import org.miser.socket.SocketRuntimeException;

/**
 * 基于AIO的Socket服务端实现
 *
 * @author Oliver
 */
public class AioServer implements Closeable {

	private static final AcceptHandler ACCEPT_HANDLER = new AcceptHandler();

	private AsynchronousChannelGroup group;
	private AsynchronousServerSocketChannel channel;
	protected SocketConfig config;
	protected AioAction<ByteBuffer> action;

	/**
	 * 构造
	 *
	 * @param port 端口
	 */
	public AioServer(int port) {
		this(port, new SocketConfig());
	}

	/**
	 * 构造
	 *
	 * @param port   端口
	 * @param config 配置项
	 */
	public AioServer(int port, SocketConfig config) {
		init(new InetSocketAddress(port), config);
	}

	/**
	 * 初始化
	 *
	 * @param address 地址和端口
	 * @param config  {@link SocketConfig} 配置项
	 * 
	 * @return this
	 */
	public AioServer init(InetSocketAddress address, SocketConfig config) {
		try {
			this.config = config;
			this.group = AsynchronousChannelGroup.withFixedThreadPool(//
					config.getThreadPoolSize(), // 默认线程池大小
					ThreadFactoryBuilder.create().setNamePrefix("aio-server-socket-").build()//
			);
			this.channel = AsynchronousServerSocketChannel.open(group).bind(address);
		} catch (IOException e) {
			throw new SocketRuntimeException(e);
		}
		return this;
	}

	/**
	 * 开始监听
	 *
	 * @param sync 是否阻塞
	 */
	public void start(boolean sync) {
		listen(sync);
	}

	/**
	 * 开始监听
	 *
	 * @param sync 是否阻塞
	 */
	public void listen(boolean sync) {
		doListen(sync);
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
	public <T> AioServer setOption(SocketOption<T> name, T value) throws IOException {
		this.channel.setOption(name, value);
		return this;
	}

	/**
	 * 获取IO处理器
	 *
	 * @return {@link AioAction}
	 */
	public AioAction<ByteBuffer> getAction() {
		return this.action;
	}

	/**
	 * 设置IO处理器，单例存在
	 *
	 * @param ioAction {@link AioAction}
	 * @return this;
	 */
	public AioServer setAction(AioAction<ByteBuffer> action) {
		this.action = action;
		return this;
	}

	/**
	 * 获取{@link AsynchronousServerSocketChannel}
	 *
	 * @return {@link AsynchronousServerSocketChannel}
	 */
	public AsynchronousServerSocketChannel getChannel() {
		return this.channel;
	}

	/**
	 * 处理接入的客户端
	 *
	 * @return this
	 */
	public AioServer accept() {
		this.channel.accept(this, ACCEPT_HANDLER);
		return this;
	}

	/**
	 * 服务是否开启状态
	 *
	 * @return 服务是否开启状态
	 */
	public boolean isOpen() {
		return (null != this.channel) && this.channel.isOpen();
	}

	/**
	 * 关闭服务
	 */
	@Override
	public void close() {
		IOUtil.close(this.channel);

		if (null != this.group && false == this.group.isShutdown()) {
			try {
				this.group.shutdownNow();
			} catch (IOException e) {
				// ignore
			}
		}

		// 结束阻塞
		synchronized (this) {
			this.notify();
		}
	}

	// -------------------------------------------------------------------------------------
	// Private method start

	/**
	 * 开始监听
	 *
	 * @param sync 是否阻塞
	 */
	private void doListen(boolean sync) {
		// 接收客户端连接
		accept();

		if (sync) {
			ThreadUtil.sync(this);
		}
	}
	// -------------------------------------------------------------------------------------
	// Private method end
}
