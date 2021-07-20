package org.miser.socket.protocol;

import java.nio.ByteBuffer;

import org.miser.socket.aio.AioSession;

/**
 * 消息解码器
 * 
 * @author Oliver
 *
 * @param <T> 解码后的目标类型
 */
public interface MesssageDecoder<T> {
	/**
	 * 对于从Socket流中获取到的数据采用当前MsgDecoder的实现类协议进行解析。
	 *
	 *
	 * @param session    本次需要解码的session
	 * @param readBuffer 待处理的读buffer
	 * @return 本次解码成功后封装的业务消息对象, 返回null则表示解码未完成
	 */
	T decode(AioSession session, ByteBuffer readBuffer);
}
