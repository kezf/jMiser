package org.miser.http.server;

import org.miser.core.swing.DesktopUtil;
import org.miser.http.ContentType;
import org.miser.http.HttpUtil;

public class BlankServerTest {
	public static void main(String[] args) {
		HttpUtil.createServer(8888).addAction("/", (req, res) -> res.write("Hello Server", ContentType.JSON.getValue()))
				.start();

		DesktopUtil.browse("http://localhost:8888/");
	}
}
