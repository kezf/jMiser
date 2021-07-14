package org.miser.extra.ftp;

import org.miser.core.io.FileUtil;
import org.miser.core.io.IOUtil;
import org.miser.core.lang.Console;
import org.miser.extra.ssh.Sftp;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class FtpTest {

	@Test
	@Ignore
	public void cdTest() {
		Ftp ftp = new Ftp("looly.centos");
		
		ftp.cd("/file/aaa");
		Console.log(ftp.pwd());
		
		IOUtil.close(ftp);
	}
	
	@Test
	@Ignore
	public void uploadTest() {
		Ftp ftp = new Ftp("looly.centos");
		
		List<String> ls = ftp.ls("/file");
		Console.log(ls);
		
		boolean upload = ftp.upload("/file/aaa", FileUtil.file("E:/qrcodeWithLogo.jpg"));
		Console.log(upload);
		
		IOUtil.close(ftp);
	}
	
	@Test
	@Ignore
	public void reconnectIfTimeoutTest() throws InterruptedException {
		Ftp ftp = new Ftp("looly.centos");

		Console.log("打印pwd: " + ftp.pwd());

		Console.log("休眠一段时间，然后再次发送pwd命令，抛出异常表明连接超时");
		Thread.sleep(35 * 1000);

		try{
			Console.log("打印pwd: " + ftp.pwd());
		}catch (FtpException e) {
			e.printStackTrace();
		}

		Console.log("判断是否超时并重连...");
		ftp.reconnectIfTimeout();

		Console.log("打印pwd: " + ftp.pwd());

		IOUtil.close(ftp);
	}

	@Test
	@Ignore
	public void recursiveDownloadFolder() {
		Ftp ftp = new Ftp("looly.centos");
		ftp.recursiveDownloadFolder("/",FileUtil.file("d:/test/download"));

		IOUtil.close(ftp);
	}

	@Test
	@Ignore
	public void recursiveDownloadFolderSftp() {
		Sftp ftp = new Sftp("127.0.0.1", 22, "test", "test");

		ftp.cd("/file/aaa");
		Console.log(ftp.pwd());
		ftp.recursiveDownloadFolder("/",FileUtil.file("d:/test/download"));

		IOUtil.close(ftp);
	}
}
