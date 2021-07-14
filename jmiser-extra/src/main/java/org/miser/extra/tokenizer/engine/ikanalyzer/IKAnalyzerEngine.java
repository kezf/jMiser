package org.miser.extra.tokenizer.engine.ikanalyzer;

import org.wltea.analyzer.core.IKSegmenter;

import org.miser.core.util.StringUtil;
import org.miser.extra.tokenizer.TokenizerEngine;
import org.miser.extra.tokenizer.Result;

/**
 * IKAnalyzer分词引擎实现<br>
 * 项目地址：https://github.com/yozhao/IKAnalyzer
 * 
 * @author Oliver
 *
 */
public class IKAnalyzerEngine implements TokenizerEngine {

	private final IKSegmenter seg;

	/**
	 * 构造
	 * 
	 */
	public IKAnalyzerEngine() {
		this(new IKSegmenter(null, true));
	}

	/**
	 * 构造
	 * 
	 * @param seg {@link IKSegmenter}
	 */
	public IKAnalyzerEngine(IKSegmenter seg) {
		this.seg = seg;
	}

	@Override
	public Result parse(CharSequence text) {
		this.seg.reset(StringUtil.getReader(text));
		return new IKAnalyzerResult(this.seg);
	}
}
