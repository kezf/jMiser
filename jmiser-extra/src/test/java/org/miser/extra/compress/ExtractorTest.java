package org.miser.extra.compress;

import org.miser.core.io.FileUtil;
import org.miser.core.util.CharsetUtil;
import org.miser.extra.compress.extractor.Extractor;
import org.junit.Ignore;
import org.junit.Test;

public class ExtractorTest {

	@Test
	@Ignore
	public void zipTest(){
		Extractor extractor = CompressUtil.createExtractor(
				CharsetUtil.defaultCharset(),
				FileUtil.file("d:/test/c_1344112734760931330_20201230104703032.zip"));

		extractor.extract(FileUtil.file("d:/test/compress/test2/"));
	}

	@Test
	@Ignore
	public void sevenZTest(){
		Extractor extractor = CompressUtil.createExtractor(
				CharsetUtil.defaultCharset(),
				FileUtil.file("d:/test/compress/test.7z"));

		extractor.extract(FileUtil.file("d:/test/compress/test2/"));
	}
}
