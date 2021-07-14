package org.miser.core.util;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.miser.core.lang.Dict;

/**
 * 字符串工具类单元测试
 *
 * @author Oliver
 */
public class StringUtilTest {

	@Test
	public void isBlankTest() {
		String blank = "	  　";
		Assert.assertTrue(StringUtil.isBlank(blank));
	}

	@Test
	public void trimTest() {
		String blank = "	 哈哈 　";
		String trim = StringUtil.trim(blank);
		Assert.assertEquals("哈哈", trim);
	}

	@Test
	public void cleanBlankTest() {
		// 包含：制表符、英文空格、不间断空白符、全角空格
		String str = "	 你 好　";
		String cleanBlank = StringUtil.cleanBlank(str);
		Assert.assertEquals("你好", cleanBlank);
	}

	@Test
	public void cutTest() {
		String str = "aaabbbcccdddaadfdfsdfsdf0";
		String[] cut = StringUtil.cut(str, 4);
		Assert.assertArrayEquals(new String[] { "aaab", "bbcc", "cddd", "aadf", "dfsd", "fsdf", "0" }, cut);
	}

	@Test
	public void splitTest() {
		String str = "a,b ,c,d,,e";
		List<String> split = StringUtil.split(str, ',', -1, true, true);
		// 测试空是否被去掉
		Assert.assertEquals(5, split.size());
		// 测试去掉两边空白符是否生效
		Assert.assertEquals("b", split.get(1));

		final String[] strings = StringUtil.splitToArray("abc/", '/');
		Assert.assertEquals(2, strings.length);
	}

	@Test
	public void splitTest2() {
		String str = "a.b.";
		List<String> split = StringUtil.split(str, '.');
		Assert.assertEquals(3, split.size());
		Assert.assertEquals("b", split.get(1));
		Assert.assertEquals("", split.get(2));
	}

	@Test
	public void splitToLongTest() {
		String str = "1,2,3,4, 5";
		long[] longArray = StringUtil.splitToLong(str, ',');
		Assert.assertArrayEquals(new long[] { 1, 2, 3, 4, 5 }, longArray);

		longArray = StringUtil.splitToLong(str, ",");
		Assert.assertArrayEquals(new long[] { 1, 2, 3, 4, 5 }, longArray);
	}

	@Test
	public void splitToIntTest() {
		String str = "1,2,3,4, 5";
		int[] intArray = StringUtil.splitToInt(str, ',');
		Assert.assertArrayEquals(new int[] { 1, 2, 3, 4, 5 }, intArray);

		intArray = StringUtil.splitToInt(str, ",");
		Assert.assertArrayEquals(new int[] { 1, 2, 3, 4, 5 }, intArray);
	}

	@Test
	public void formatTest() {
		String template = "你好，我是{name}，我的电话是：{phone}";
		String result = StringUtil.format(template, Dict.create().set("name", "张三").set("phone", "13888881111"));
		Assert.assertEquals("你好，我是张三，我的电话是：13888881111", result);

		String result2 = StringUtil.format(template, Dict.create().set("name", "张三").set("phone", null));
		Assert.assertEquals("你好，我是张三，我的电话是：{phone}", result2);
	}

	@Test
	public void stripTest() {
		String str = "abcd123";
		String strip = StringUtil.strip(str, "ab", "23");
		Assert.assertEquals("cd1", strip);

		str = "abcd123";
		strip = StringUtil.strip(str, "ab", "");
		Assert.assertEquals("cd123", strip);

		str = "abcd123";
		strip = StringUtil.strip(str, null, "");
		Assert.assertEquals("abcd123", strip);

		str = "abcd123";
		strip = StringUtil.strip(str, null, "567");
		Assert.assertEquals("abcd123", strip);

		Assert.assertEquals("", StringUtil.strip("a", "a"));
		Assert.assertEquals("", StringUtil.strip("a", "a", "b"));
	}

	@Test
	public void stripIgnoreCaseTest() {
		String str = "abcd123";
		String strip = StringUtil.stripIgnoreCase(str, "Ab", "23");
		Assert.assertEquals("cd1", strip);

		str = "abcd123";
		strip = StringUtil.stripIgnoreCase(str, "AB", "");
		Assert.assertEquals("cd123", strip);

		str = "abcd123";
		strip = StringUtil.stripIgnoreCase(str, "ab", "");
		Assert.assertEquals("cd123", strip);

		str = "abcd123";
		strip = StringUtil.stripIgnoreCase(str, null, "");
		Assert.assertEquals("abcd123", strip);

		str = "abcd123";
		strip = StringUtil.stripIgnoreCase(str, null, "567");
		Assert.assertEquals("abcd123", strip);
	}

	@Test
	public void indexOfIgnoreCaseTest() {
		Assert.assertEquals(-1, StringUtil.indexOfIgnoreCase(null, "balabala", 0));
		Assert.assertEquals(-1, StringUtil.indexOfIgnoreCase("balabala", null, 0));
		Assert.assertEquals(0, StringUtil.indexOfIgnoreCase("", "", 0));
		Assert.assertEquals(0, StringUtil.indexOfIgnoreCase("aabaabaa", "A", 0));
		Assert.assertEquals(2, StringUtil.indexOfIgnoreCase("aabaabaa", "B", 0));
		Assert.assertEquals(1, StringUtil.indexOfIgnoreCase("aabaabaa", "AB", 0));
		Assert.assertEquals(5, StringUtil.indexOfIgnoreCase("aabaabaa", "B", 3));
		Assert.assertEquals(-1, StringUtil.indexOfIgnoreCase("aabaabaa", "B", 9));
		Assert.assertEquals(2, StringUtil.indexOfIgnoreCase("aabaabaa", "B", -1));
		Assert.assertEquals(2, StringUtil.indexOfIgnoreCase("aabaabaa", "", 2));
		Assert.assertEquals(-1, StringUtil.indexOfIgnoreCase("abc", "", 9));
	}

	@Test
	public void lastIndexOfTest() {
		String a = "aabbccddcc";
		int lastIndexOf = StringUtil.lastIndexOf(a, "c", 0, false);
		Assert.assertEquals(-1, lastIndexOf);
	}

	@Test
	public void lastIndexOfIgnoreCaseTest() {
		Assert.assertEquals(-1, StringUtil.lastIndexOfIgnoreCase(null, "balabala", 0));
		Assert.assertEquals(-1, StringUtil.lastIndexOfIgnoreCase("balabala", null));
		Assert.assertEquals(0, StringUtil.lastIndexOfIgnoreCase("", ""));
		Assert.assertEquals(7, StringUtil.lastIndexOfIgnoreCase("aabaabaa", "A"));
		Assert.assertEquals(5, StringUtil.lastIndexOfIgnoreCase("aabaabaa", "B"));
		Assert.assertEquals(4, StringUtil.lastIndexOfIgnoreCase("aabaabaa", "AB"));
		Assert.assertEquals(2, StringUtil.lastIndexOfIgnoreCase("aabaabaa", "B", 3));
		Assert.assertEquals(5, StringUtil.lastIndexOfIgnoreCase("aabaabaa", "B", 9));
		Assert.assertEquals(-1, StringUtil.lastIndexOfIgnoreCase("aabaabaa", "B", -1));
		Assert.assertEquals(2, StringUtil.lastIndexOfIgnoreCase("aabaabaa", "", 2));
		Assert.assertEquals(3, StringUtil.lastIndexOfIgnoreCase("abc", "", 9));
		Assert.assertEquals(0, StringUtil.lastIndexOfIgnoreCase("AAAcsd", "aaa"));
	}

	@Test
	public void replaceTest() {
		String string = StringUtil.replace("aabbccdd", 2, 6, '*');
		Assert.assertEquals("aa****dd", string);
		string = StringUtil.replace("aabbccdd", 2, 12, '*');
		Assert.assertEquals("aa******", string);
	}

	@Test
	public void replaceTest2() {
		String result = StringUtil.replace("123", "2", "3");
		Assert.assertEquals("133", result);
	}

	@Test
	public void replaceTest3() {
		String result = StringUtil.replace(",abcdef,", ",", "|");
		Assert.assertEquals("|abcdef|", result);
	}

	@Test
	public void replaceTest4() {
		String a = "1039";
		String result = StringUtil.padPre(a, 8, "0"); // 在字符串1039前补4个0
		Assert.assertEquals("00001039", result);

		String aa = "1039";
		String result1 = StringUtil.padPre(aa, -1, "0"); // 在字符串1039前补4个0
		Assert.assertEquals("103", result1);
	}

	@Test
	public void upperFirstTest() {
		StringBuilder sb = new StringBuilder("KEY");
		String s = StringUtil.upperFirst(sb);
		Assert.assertEquals(s, sb.toString());
	}

	@Test
	public void lowerFirstTest() {
		StringBuilder sb = new StringBuilder("KEY");
		String s = StringUtil.lowerFirst(sb);
		Assert.assertEquals("kEY", s);
	}

	@Test
	public void subTest() {
		String a = "abcderghigh";
		String pre = StringUtil.sub(a, -5, a.length());
		Assert.assertEquals("ghigh", pre);
	}

	@Test
	public void subByCodePointTest() {
		// 🤔👍🍓🤔
		String test = "\uD83E\uDD14\uD83D\uDC4D\uD83C\uDF53\uD83E\uDD14";

		// 不正确的子字符串
		String wrongAnswer = StringUtil.sub(test, 0, 3);
		Assert.assertNotEquals("\uD83E\uDD14\uD83D\uDC4D\uD83C\uDF53", wrongAnswer);

		// 正确的子字符串
		String rightAnswer = StringUtil.subByCodePoint(test, 0, 3);
		Assert.assertEquals("\uD83E\uDD14\uD83D\uDC4D\uD83C\uDF53", rightAnswer);
	}

	@Test
	public void subBeforeTest() {
		String a = "abcderghigh";
		String pre = StringUtil.subBefore(a, "d", false);
		Assert.assertEquals("abc", pre);
		pre = StringUtil.subBefore(a, 'd', false);
		Assert.assertEquals("abc", pre);
		pre = StringUtil.subBefore(a, 'a', false);
		Assert.assertEquals("", pre);

		// 找不到返回原串
		pre = StringUtil.subBefore(a, 'k', false);
		Assert.assertEquals(a, pre);
		pre = StringUtil.subBefore(a, 'k', true);
		Assert.assertEquals(a, pre);
	}

	@Test
	public void subAfterTest() {
		String a = "abcderghigh";
		String pre = StringUtil.subAfter(a, "d", false);
		Assert.assertEquals("erghigh", pre);
		pre = StringUtil.subAfter(a, 'd', false);
		Assert.assertEquals("erghigh", pre);
		pre = StringUtil.subAfter(a, 'h', true);
		Assert.assertEquals("", pre);

		// 找不到字符返回空串
		pre = StringUtil.subAfter(a, 'k', false);
		Assert.assertEquals("", pre);
		pre = StringUtil.subAfter(a, 'k', true);
		Assert.assertEquals("", pre);
	}

	@Test
	public void subSufByLengthTest() {
		Assert.assertEquals("cde", StringUtil.subSufByLength("abcde", 3));
		Assert.assertEquals("", StringUtil.subSufByLength("abcde", -1));
		Assert.assertEquals("", StringUtil.subSufByLength("abcde", 0));
		Assert.assertEquals("abcde", StringUtil.subSufByLength("abcde", 5));
		Assert.assertEquals("abcde", StringUtil.subSufByLength("abcde", 10));
	}

	@Test
	public void repeatAndJoinTest() {
		String repeatAndJoin = StringUtil.repeatAndJoin("?", 5, ",");
		Assert.assertEquals("?,?,?,?,?", repeatAndJoin);

		repeatAndJoin = StringUtil.repeatAndJoin("?", 0, ",");
		Assert.assertEquals("", repeatAndJoin);

		repeatAndJoin = StringUtil.repeatAndJoin("?", 5, null);
		Assert.assertEquals("?????", repeatAndJoin);
	}

	@Test
	public void moveTest() {
		String str = "aaaaaaa22222bbbbbbb";
		String result = StringUtil.move(str, 7, 12, -3);
		Assert.assertEquals("aaaa22222aaabbbbbbb", result);
		result = StringUtil.move(str, 7, 12, -4);
		Assert.assertEquals("aaa22222aaaabbbbbbb", result);
		result = StringUtil.move(str, 7, 12, -7);
		Assert.assertEquals("22222aaaaaaabbbbbbb", result);
		result = StringUtil.move(str, 7, 12, -20);
		Assert.assertEquals("aaaaaa22222abbbbbbb", result);

		result = StringUtil.move(str, 7, 12, 3);
		Assert.assertEquals("aaaaaaabbb22222bbbb", result);
		result = StringUtil.move(str, 7, 12, 7);
		Assert.assertEquals("aaaaaaabbbbbbb22222", result);
		result = StringUtil.move(str, 7, 12, 20);
		Assert.assertEquals("aaaaaaab22222bbbbbb", result);

		result = StringUtil.move(str, 7, 12, 0);
		Assert.assertEquals("aaaaaaa22222bbbbbbb", result);
	}

	@Test
	public void removePrefixIgnorecaseTest() {
		String a = "aaabbb";
		String prefix = "aaa";
		Assert.assertEquals("bbb", StringUtil.removePrefixIgnoreCase(a, prefix));

		prefix = "AAA";
		Assert.assertEquals("bbb", StringUtil.removePrefixIgnoreCase(a, prefix));

		prefix = "AAABBB";
		Assert.assertEquals("", StringUtil.removePrefixIgnoreCase(a, prefix));
	}

	@Test
	public void maxLengthTest() {
		String text = "我是一段正文，很长的正文，需要截取的正文";
		String str = StringUtil.maxLength(text, 5);
		Assert.assertEquals("我是一段正...", str);
		str = StringUtil.maxLength(text, 21);
		Assert.assertEquals(text, str);
		str = StringUtil.maxLength(text, 50);
		Assert.assertEquals(text, str);
	}

	@Test
	public void toCamelCaseTest() {
		String str = "Table_Test_Of_day";
		String result = StringUtil.toCamelCase(str);
		Assert.assertEquals("tableTestOfDay", result);

		String str1 = "TableTestOfDay";
		String result1 = StringUtil.toCamelCase(str1);
		Assert.assertEquals("TableTestOfDay", result1);

		String abc1d = StringUtil.toCamelCase("abc_1d");
		Assert.assertEquals("abc1d", abc1d);
	}

	@Test
	public void toUnderLineCaseTest() {
		Dict.create().set("Table_Test_Of_day", "table_test_of_day").set("_Table_Test_Of_day_", "_table_test_of_day_")
				.set("_Table_Test_Of_DAY_", "_table_test_of_DAY_")
				.set("_TableTestOfDAYtoday", "_table_test_of_DAY_today").set("HelloWorld_test", "hello_world_test")
				.set("H2", "H2").set("H#case", "H#case")
				.forEach((key, value) -> Assert.assertEquals(value, StringUtil.toUnderlineCase(key)));
	}

	@Test
	public void containsAnyTest() {
		// 字符
		boolean containsAny = StringUtil.containsAny("aaabbbccc", 'a', 'd');
		Assert.assertTrue(containsAny);
		containsAny = StringUtil.containsAny("aaabbbccc", 'e', 'd');
		Assert.assertFalse(containsAny);
		containsAny = StringUtil.containsAny("aaabbbccc", 'd', 'c');
		Assert.assertTrue(containsAny);

		// 字符串
		containsAny = StringUtil.containsAny("aaabbbccc", "a", "d");
		Assert.assertTrue(containsAny);
		containsAny = StringUtil.containsAny("aaabbbccc", "e", "d");
		Assert.assertFalse(containsAny);
		containsAny = StringUtil.containsAny("aaabbbccc", "d", "c");
		Assert.assertTrue(containsAny);
	}

	@Test
	public void centerTest() {
		Assert.assertNull(StringUtil.center(null, 10));
		Assert.assertEquals("    ", StringUtil.center("", 4));
		Assert.assertEquals("ab", StringUtil.center("ab", -1));
		Assert.assertEquals(" ab ", StringUtil.center("ab", 4));
		Assert.assertEquals("abcd", StringUtil.center("abcd", 2));
		Assert.assertEquals(" a  ", StringUtil.center("a", 4));
	}

	@Test
	public void padPreTest() {
		Assert.assertNull(StringUtil.padPre(null, 10, ' '));
		Assert.assertEquals("001", StringUtil.padPre("1", 3, '0'));
		Assert.assertEquals("12", StringUtil.padPre("123", 2, '0'));

		Assert.assertNull(StringUtil.padPre(null, 10, "AA"));
		Assert.assertEquals("AB1", StringUtil.padPre("1", 3, "ABC"));
		Assert.assertEquals("12", StringUtil.padPre("123", 2, "ABC"));
	}

	@Test
	public void padAfterTest() {
		Assert.assertNull(StringUtil.padAfter(null, 10, ' '));
		Assert.assertEquals("100", StringUtil.padAfter("1", 3, '0'));
		Assert.assertEquals("23", StringUtil.padAfter("123", 2, '0'));
		Assert.assertEquals("", StringUtil.padAfter("123", -1, '0'));

		Assert.assertNull(StringUtil.padAfter(null, 10, "ABC"));
		Assert.assertEquals("1AB", StringUtil.padAfter("1", 3, "ABC"));
		Assert.assertEquals("23", StringUtil.padAfter("123", 2, "ABC"));
	}

	@Test
	public void subBetweenAllTest() {
		Assert.assertArrayEquals(new String[] { "yz", "abc" },
				StringUtil.subBetweenAll("saho[yz]fdsadp[abc]a", "[", "]"));
		Assert.assertArrayEquals(new String[] { "abc" }, StringUtil.subBetweenAll("saho[yzfdsadp[abc]a]", "[", "]"));
		Assert.assertArrayEquals(new String[] { "abc", "abc" }, StringUtil.subBetweenAll("yabczyabcz", "y", "z"));
		Assert.assertArrayEquals(new String[0], StringUtil.subBetweenAll(null, "y", "z"));
		Assert.assertArrayEquals(new String[0], StringUtil.subBetweenAll("", "y", "z"));
		Assert.assertArrayEquals(new String[0], StringUtil.subBetweenAll("abc", null, "z"));
		Assert.assertArrayEquals(new String[0], StringUtil.subBetweenAll("abc", "y", null));
	}

	@Test
	public void subBetweenAllTest2() {
		// issue#861@Github，起始不匹配的时候，应该直接空
		String src1 = "/* \n* hello  */  asdas  /* \n* hello  */";
		String src2 = "/ * hello  */  asdas  / * hello  */";

		String[] results1 = StringUtil.subBetweenAll(src1, "/**", "*/");
		Assert.assertEquals(0, results1.length);

		String[] results2 = StringUtil.subBetweenAll(src2, "/*", "*/");
		Assert.assertEquals(0, results2.length);
	}

	@Test
	public void subBetweenAllTest3() {
		String src1 = "'abc'and'123'";
		String[] strings = StringUtil.subBetweenAll(src1, "'", "'");
		Assert.assertEquals(2, strings.length);
		Assert.assertEquals("abc", strings[0]);
		Assert.assertEquals("123", strings[1]);

		String src2 = "'abc''123'";
		strings = StringUtil.subBetweenAll(src2, "'", "'");
		Assert.assertEquals(2, strings.length);
		Assert.assertEquals("abc", strings[0]);
		Assert.assertEquals("123", strings[1]);

		String src3 = "'abc'123'";
		strings = StringUtil.subBetweenAll(src3, "'", "'");
		Assert.assertEquals(1, strings.length);
		Assert.assertEquals("abc", strings[0]);
	}

	@Test
	public void briefTest() {
		String str = RandomUtil.randomString(1000);
		int maxLength = RandomUtil.randomInt(1000);
		String brief = StringUtil.brief(str, maxLength);
		Assert.assertEquals(brief.length(), maxLength);
	}

	@Test
	public void filterTest() {
		final String filterNumber = StringUtil.filter("world678", CharUtil::isNumber);
		Assert.assertEquals("678", filterNumber);
		String cleanBlank = StringUtil.filter("	 你 好　", c -> !CharUtil.isBlankChar(c));
		Assert.assertEquals("你好", cleanBlank);
	}

	@Test
	public void wrapAllTest() {
		String[] strings = StringUtil.wrapAll("`", "`", StringUtil.splitToArray("1,2,3,4", ','));
		Assert.assertEquals("[`1`, `2`, `3`, `4`]", StringUtil.utf8Str(strings));

		strings = StringUtil.wrapAllWithPair("`", StringUtil.splitToArray("1,2,3,4", ','));
		Assert.assertEquals("[`1`, `2`, `3`, `4`]", StringUtil.utf8Str(strings));
	}

	@Test
	public void startWithTest() {
		String a = "123";
		String b = "123";

		Assert.assertTrue(StringUtil.startWith(a, b));
		Assert.assertFalse(StringUtil.startWithIgnoreEquals(a, b));
	}

	@Test
	public void indexedFormatTest() {
		final String ret = StringUtil.indexedFormat("this is {0} for {1}", "a", 1000);
		Assert.assertEquals("this is a for 1,000", ret);
	}

	@Test
	public void hideTest() {
		Assert.assertNull(StringUtil.hide(null, 1, 1));
		Assert.assertEquals("", StringUtil.hide("", 1, 1));
		Assert.assertEquals("****duan@163.com", StringUtil.hide("jackduan@163.com", -1, 4));
		Assert.assertEquals("ja*kduan@163.com", StringUtil.hide("jackduan@163.com", 2, 3));
		Assert.assertEquals("jackduan@163.com", StringUtil.hide("jackduan@163.com", 3, 2));
		Assert.assertEquals("jackduan@163.com", StringUtil.hide("jackduan@163.com", 16, 16));
		Assert.assertEquals("jackduan@163.com", StringUtil.hide("jackduan@163.com", 16, 17));
	}

	@Test
	public void isCharEqualsTest() {
		String a = "aaaaaaaaa";
		Assert.assertTrue(StringUtil.isCharEquals(a));
	}

	@Test
	public void isNumericTest() {
		String a = "2142342422423423";
		Assert.assertTrue(StringUtil.isNumeric(a));
	}
}
