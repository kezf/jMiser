package org.miser.core.text.replacer;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.miser.core.lang.Chain;
import org.miser.core.text.FastStringBuilder;

/**
 * 字符串替换链，用于组合多个字符串替换逻辑
 *
 * @author Oliver
 * 
 */
public class ReplacerChain extends StringReplacer implements Chain<StringReplacer, ReplacerChain> {
	private static final long serialVersionUID = 1L;

	private final List<StringReplacer> replacers = new LinkedList<>();

	/**
	 * 构造
	 *
	 * @param strReplacers 字符串替换器
	 */
	public ReplacerChain(StringReplacer... strReplacers) {
		for (StringReplacer strReplacer : strReplacers) {
			addChain(strReplacer);
		}
	}

	@SuppressWarnings("NullableProblems")
	@Override
	public Iterator<StringReplacer> iterator() {
		return replacers.iterator();
	}

	@Override
	public ReplacerChain addChain(StringReplacer element) {
		replacers.add(element);
		return this;
	}

	@Override
	protected int replace(CharSequence str, int pos, FastStringBuilder out) {
		int consumed = 0;
		for (StringReplacer strReplacer : replacers) {
			consumed = strReplacer.replace(str, pos, out);
			if (0 != consumed) {
				return consumed;
			}
		}
		return consumed;
	}

}
