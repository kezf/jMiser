package org.miser.core.text.csv;

import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;
import org.miser.core.io.IOUtil;
import org.miser.core.util.StringUtil;

public class CsvParserTest {

	@Test
	public void parseTest1() {
		StringReader reader = StringUtil.getReader("aaa,b\"bba\",ccc");
		CsvParser parser = new CsvParser(reader, null);
		CsvRow row = parser.nextRow();
		// noinspection ConstantConditions
		Assert.assertEquals("b\"bba\"", row.getRawList().get(1));
		IOUtil.close(parser);
	}

	@Test
	public void parseTest2() {
		StringReader reader = StringUtil.getReader("aaa,\"bba\"bbb,ccc");
		CsvParser parser = new CsvParser(reader, null);
		CsvRow row = parser.nextRow();
		// noinspection ConstantConditions
		Assert.assertEquals("\"bba\"bbb", row.getRawList().get(1));
		IOUtil.close(parser);
	}

	@Test
	public void parseTest3() {
		StringReader reader = StringUtil.getReader("aaa,\"bba\",ccc");
		CsvParser parser = new CsvParser(reader, null);
		CsvRow row = parser.nextRow();
		// noinspection ConstantConditions
		Assert.assertEquals("bba", row.getRawList().get(1));
		IOUtil.close(parser);
	}

	@Test
	public void parseTest4() {
		StringReader reader = StringUtil.getReader("aaa,\"\",ccc");
		CsvParser parser = new CsvParser(reader, null);
		CsvRow row = parser.nextRow();
		// noinspection ConstantConditions
		Assert.assertEquals("", row.getRawList().get(1));
		IOUtil.close(parser);
	}
}
