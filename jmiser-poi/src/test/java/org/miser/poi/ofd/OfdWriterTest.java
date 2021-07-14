package org.miser.poi.ofd;

import org.miser.core.io.FileUtil;
import org.junit.Ignore;
import org.junit.Test;

public class OfdWriterTest {

	@Test
	@Ignore
	public void writeTest(){
		final OfdWriter ofdWriter = new OfdWriter(FileUtil.file("d:/test/test.ofd"));
		ofdWriter.addText(null, "测试文本");
		ofdWriter.close();
	}
}
