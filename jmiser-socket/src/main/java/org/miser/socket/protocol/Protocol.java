package org.miser.socket.protocol;

/**
 * 协议接口<br>
 * 通过实现此接口完成消息的编码和解码
 * 
 * <p>
 * 所有Socket使用相同协议对象，类成员变量和对象成员变量易造成并发读写问题。
 * </p>
 *
 * @author Oliver
 */
public interface Protocol<T> extends MessageEncoder<T>, MesssageDecoder<T> {

}
