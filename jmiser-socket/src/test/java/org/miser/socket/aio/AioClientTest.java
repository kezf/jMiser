package org.miser.socket.aio;

import java.nio.ByteBuffer;

import org.miser.core.lang.Console;
import org.miser.core.util.StringUtil;

public class AioClientTest {
	public static void main(String[] args) {
		AioClient client = new AioClient("localhost", 8899, new SimpleAioAction() {

			@Override
			public void doAction(AioSession session, ByteBuffer data) {
				if (data.hasRemaining()) {
					Console.log(StringUtil.utf8Str(data));
					session.read();
				}
				Console.log("OK");
			}
		});

		client.write(ByteBuffer.wrap("Hello".getBytes()));
		client.read();

		client.close();
	}
}
