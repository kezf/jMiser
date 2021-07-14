package org.miser.http.server;

import org.miser.core.swing.DesktopUtil;
import org.miser.http.HttpUtil;

public class DocServerTest {

	public static void main(String[] args) {
		HttpUtil.createServer(80)
				// 设置默认根目录，
				.setRoot("D:\\workspace\\site\\jmiser-site")
				.start();

		DesktopUtil.browse("http://localhost/");
	}
}
