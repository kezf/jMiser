package org.miser.core.io;

import org.junit.Assert;
import org.junit.Test;
import org.miser.core.io.file.FileReader;

/**
 * 文件读取测试
 * 
 * @author Oliver
 *
 */
public class FileReaderTest {

	@Test
	public void fileReaderTest() {
		FileReader fileReader = new FileReader("test.properties");
		String result = fileReader.readString();
		Assert.assertNotNull(result);
	}
}
