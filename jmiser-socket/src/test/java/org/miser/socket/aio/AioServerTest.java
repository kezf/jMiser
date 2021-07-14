package org.miser.socket.aio;

import org.miser.core.date.DateUtil;
import org.miser.core.io.BufferUtil;
import org.miser.core.lang.Console;
import org.miser.core.util.StringUtil;
import org.miser.log.StaticLog;

import java.nio.ByteBuffer;

public class AioServerTest {
	
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		AioServer aioServer = new AioServer(8899);
		aioServer.setIoAction(new SimpleIoAction() {
			
			@Override
			public void accept(AioSession session) {
				StaticLog.debug("【客户端】：{} 连接。", session.getRemoteAddress());
				session.write(BufferUtil.createUtf8("=== Welcome to socket server. ==="));
			}
			
			@Override
			public void doAction(AioSession session, ByteBuffer data) {
				Console.log(data);
				
				if(false == data.hasRemaining()) {
					StringBuilder response = StringUtil.builder()//
							.append("HTTP/1.1 200 OK\r\n")//
							.append("Date: ").append(DateUtil.formatHttpDate(DateUtil.date())).append("\r\n")//
							.append("Content-Type: text/html; charset=UTF-8\r\n")//
							.append("\r\n")
							.append("Hello socket");//
					session.writeAndClose(BufferUtil.createUtf8(response));
				}else {
					session.read();
				}
			}
		}).start(true);
	}
}
